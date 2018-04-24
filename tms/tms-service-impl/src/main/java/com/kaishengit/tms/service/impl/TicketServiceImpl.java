package com.kaishengit.tms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.TicketInRecordMapper;
import com.kaishengit.tms.mapper.TicketMapper;
import com.kaishengit.tms.mapper.TicketOutRecordMapper;
import com.kaishengit.tms.mapper.TicketStoreMapper;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.util.shiro.ShiroUtil;
import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {

    public static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private ShiroUtil shiroUtil;

    @Autowired
    private TicketInRecordMapper ticketInRecordMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketOutRecordMapper ticketOutRecordMapper;
    @Autowired
    private TicketStoreMapper ticketStoreMapper;


    /**
     * 保存一个入库记录
     * @param ticketInRecord
     * @return void
     * @date 2018/4/21
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTicketInRecord(TicketInRecord ticketInRecord) {


        //获得 截止票号 和 起始票号
        BigInteger start = new BigInteger(ticketInRecord.getBeginTicketNum());
        BigInteger end = new BigInteger(ticketInRecord.getEndTicketNum());
        //判断起始票号是否小于截止票号
        if (start.compareTo(end)>=0){
            throw new ServiceException("起始票号必须小于截止票号");
        }
        //判断当前的入库票号的范围是否和之前的入库的范围重合，如果重合则不能添加
        List<TicketInRecord> ticketInRecordList = ticketInRecordMapper.selectByExample(new TicketInRecordExample());
        for (TicketInRecord record:ticketInRecordList){
            BigInteger recordStart = new BigInteger(record.getBeginTicketNum());
            BigInteger recordEnd = new BigInteger(record.getEndTicketNum());
            //如果起始票号在以往入库票段开始和结束之间  或者 结束票号在以往入库票段开始和结束之间 则代表重复
            boolean flag = (recordStart.compareTo(start)<=0 && recordEnd.compareTo(start)>=0) || (recordStart.compareTo(end)<=0 && recordEnd.compareTo(end)>= 0);
            if (flag){
                throw new ServiceException("票号区间重复，添加失败");
            }
        }

        //设置入库时间
        ticketInRecord.setCreateTime(new Date());
        //设置总数量 = 截止票号 - 起始票号 + 1
        BigInteger totalNum = end.subtract(start).add(new BigInteger(String.valueOf(1)));
        ticketInRecord.setTotalNum(totalNum.intValue());

        //获取当前登录对象
        Account account = shiroUtil.getCurrentAccount();
        ticketInRecord.setAccountId(account.getId());
        ticketInRecord.setAccountName(account.getAccountName());

        //设置入库的内容
        ticketInRecord.setContent(ticketInRecord.getBeginTicketNum()+"-"+ticketInRecord.getEndTicketNum());

        ticketInRecordMapper.insertSelective(ticketInRecord);

        logger.info("新增年票入库:{},入库人:{}",ticketInRecord,account);

        //添加totalNum条年票记录
        List<Ticket> ticketList = new ArrayList<>();
        for (int i=0;i<totalNum.intValue();i++){
            Ticket ticket = new Ticket();
            ticket.setCreateTime(new Date());
            ticket.setTicketInTime(new Date());
            ticket.setTicketNum(start.add(new BigInteger(String.valueOf(i))).toString());
            ticket.setTicketState(Ticket.TICKET_STATE_IN_STORE);
            ticket.setTicketInRecordId(ticketInRecord.getId());

            ticketList.add(ticket);
        }

        //批量保存年票记录
        ticketMapper.batchInsert(ticketList);
    }

    /**
     * 查找所有入库记录
     *
     * @return java.util.List<com.kaishengit.tms.entity.TicketInRecord>
     * @date 2018/4/21
     */
    @Override
    public List<TicketInRecord> findAllTicketInRecord() {
        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();
        return ticketInRecordMapper.selectByExample(ticketInRecordExample);
    }

    /**
     * 根据id删除入库记录
     * @param id
     * @return void
     * @date 2018/4/21
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delTicketInRecordById(Integer id) {
        //查询ticketInRecord对象
        TicketInRecord ticketInRecord = ticketInRecordMapper.selectByPrimaryKey(id);
        //在不知道前端是不是ajax请求的情况下,防止用户篡改url中id的值,修改和删除都要增加
        if (ticketInRecord==null){
            throw new ServiceException("参数异常,删除失败");
        }

        //根据入库记录删除年票ticket对象
            //1.根据入库记录id 和 票号状态为正常 查询年票集合
        /*TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketInRecordIdEqualTo(ticketInRecord.getId()).andTicketStateEqualTo(Ticket.TICKET_STATE_IN_STORE);
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);*/

        List<Ticket> ticketList = ticketMapper.selectByStartAndEndNum(ticketInRecord.getBeginTicketNum(),ticketInRecord.getEndTicketNum(),Ticket.TICKET_STATE_IN_STORE);

            //2.判断ticket集合和ticketInRocord对象的数量是否相同，如果相同则票的状态未变
        if (!ticketInRecord.getTotalNum().equals(ticketList.size())){
            throw new ServiceException("该批次年票状态发生改变，不能删除");
        }

        //根据id删除入库记录ticketInRecord 和对应年票
        ticketMapper.deleteByStartAndEndNum(ticketInRecord.getBeginTicketNum(),ticketInRecord.getEndTicketNum());

        ticketInRecordMapper.deleteByPrimaryKey(id);

    }

    @Override
    public TicketInRecord findTicketInRecordById(Integer id) {
        return ticketInRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改入库记录
     * @param ticketInRecord
     * @return void
     * @date 2018/4/21
     */
    @Override
    public void updateTicketInRecord(TicketInRecord ticketInRecord) {

        //设置入库修改时间
        ticketInRecord.setUpdateTime(new Date());
        //设置总数量 = 截止票号 - 起始票号 + 1
        BigInteger start = new BigInteger(ticketInRecord.getBeginTicketNum());
        BigInteger end = new BigInteger(ticketInRecord.getEndTicketNum());

        BigInteger totalNum = end.subtract(start).add(new BigInteger(String.valueOf(1)));
        ticketInRecord.setTotalNum(totalNum.intValue());

        //获取当前登录对象
        Account account = shiroUtil.getCurrentAccount();
        ticketInRecord.setAccountId(account.getId());
        ticketInRecord.setAccountName(account.getAccountName());

        //设置入库的内容
        ticketInRecord.setContent(ticketInRecord.getBeginTicketNum()+"-"+ticketInRecord.getEndTicketNum());

        ticketInRecordMapper.updateByPrimaryKeySelective(ticketInRecord);

        logger.info("修改年票入库:{},入库人:{}",ticketInRecord,account);

        //删除原年票入库记录对应的年票记录
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketInRecordIdEqualTo(ticketInRecord.getId());
        //新增新年票入库记录对应的年票记录
        List<Ticket> ticketList = new ArrayList<>();
        for (int i=0;i<totalNum.intValue();i++){
            Ticket ticket = new Ticket();
            ticket.setCreateTime(new Date());
            ticket.setTicketInTime(new Date());
            ticket.setTicketNum(start.add(new BigInteger(String.valueOf(i))).toString());
            ticket.setTicketState(Ticket.TICKET_STATE_IN_STORE);
            ticket.setTicketInRecordId(ticketInRecord.getId());

            ticketList.add(ticket);
        }

        //批量保存新增年票记录
        ticketMapper.batchInsert(ticketList);

    }

    /**
     * 根据页码查询入库记录
     *
     * @param pageNo
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketInRecord>
     * @date 2018/4/23
     */
    @Override
    public PageInfo<TicketInRecord> findTicketRecordByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,15);

        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();
        ticketInRecordExample.setOrderByClause("id desc");

        List<TicketInRecord> ticketInRecordList = ticketInRecordMapper.selectByExample(ticketInRecordExample);
        return new PageInfo<>(ticketInRecordList);
    }

    /*----------------------------------年票统计----------------------------------------*/

    /**
     * 根据年票状态统计年票数量
     *
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @date 2018/4/23
     */
    @Override
    public Map<String, Object> countTicketByState() {
        return ticketMapper.countTicketByState();
    }


    /*---------------------------------年票下发-------------------------------------*/

    /**
     * 根据页码查询下发记录
     *
     * @param pageNo
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketOutRecord>
     * @date 2018/4/23
     */
    @Override
    public PageInfo<TicketOutRecord> findTicketOutRecordByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,15);

        TicketOutRecordExample ticketOutRecordExample = new TicketOutRecordExample();
        ticketOutRecordExample.setOrderByClause("id desc");

        List<TicketOutRecord> ticketOutRecordList = ticketOutRecordMapper.selectByExample(ticketOutRecordExample);
        return new PageInfo<>(ticketOutRecordList);
    }

    /**
     * 新增下发记录
     * @param ticketOutRecord
     * @return void
     * @date 2018/4/23
     */
    @Override
    public void saveTicketOutRecord(TicketOutRecord ticketOutRecord) {
        //判断当前票段内是否有非[已入库]状态的表，如果有则不能不发


        List<Ticket> ticketList = ticketMapper.selectOutByStartAndEndNum(ticketOutRecord.getBeginTicketNum(),ticketOutRecord.getEndTicketNum());

        for (Ticket ticket:ticketList){
            if (!Ticket.TICKET_STATE_IN_STORE.equals(ticket.getTicketState())){
                throw new ServiceException("该票段内已有下发的票，请重新选择");
            }
        }

        //获取当前下发的售票点对象，并赋值售票点名称
        TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(ticketOutRecord.getStoreAccountId());
        ticketOutRecord.setStoreAccountName(ticketStore.getStoreName());
        //选择总数量
        int totalSize = ticketList.size();
        //总价格
        BigDecimal totalPrice = ticketOutRecord.getPrice().multiply(new BigDecimal(totalSize));
        //当前登录对象
        Account account = shiroUtil.getCurrentAccount();

        ticketOutRecord.setCreateTime(new Date());
        ticketOutRecord.setContent(ticketOutRecord.getBeginTicketNum() +"-"+ticketOutRecord.getEndTicketNum());
        ticketOutRecord.setOutAccountId(account.getId());
        ticketOutRecord.setOutAccountName(account.getAccountName());
        ticketOutRecord.setTotalNum(totalSize);
        ticketOutRecord.setState(TicketOutRecord.STATE_NO_PAY);
        ticketOutRecord.setTotalPrice(totalPrice);

        ticketOutRecordMapper.insertSelective(ticketOutRecord);
        logger.info("新增年票下发记录：{}",ticketOutRecord);



    }

    /**
     * 删除下发单
     * @param id
     * @return void
     * @date 2018/4/23
     */
    @Override
    public void delTicketOutRecordById(Integer id) {
        TicketOutRecord ticketOutRecord = ticketOutRecordMapper.selectByPrimaryKey(id);
        if (ticketOutRecord == null){
            throw new ServiceException("参数异常,删除失败");
        }
        if (TicketOutRecord.STATE_NO_PAY.equals(ticketOutRecord.getState())){
            ticketOutRecordMapper.deleteByPrimaryKey(id);
        } else {
            throw new ServiceException("支付完成，无法删除");
        }

    }
}
