package com.kaishengit.tms.service.impl;

import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.mapper.TicketInRecordMapper;
import com.kaishengit.tms.mapper.TicketMapper;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.util.shiro.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    public static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private ShiroUtil shiroUtil;

    @Autowired
    private TicketInRecordMapper ticketInRecordMapper;
    @Autowired
    private TicketMapper ticketMapper;


    /**
     * 保存一个入库记录
     * @param ticketInRecord
     * @return void
     * @date 2018/4/21
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTicketInRecord(TicketInRecord ticketInRecord) {

        //设置入库时间
        ticketInRecord.setCreateTime(new Date());
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
     *
     * @param id
     * @return void
     * @date 2018/4/21
     */
    @Override
    public void delTicketInRecordById(Integer id) {
        //查询ticketInRecord对象
        TicketInRecord ticketInRecord = ticketInRecordMapper.selectByPrimaryKey(id);

        //根据id删除入库记录ticketInRecord
        ticketInRecordMapper.deleteByPrimaryKey(id);

        //根据入库记录删除年票ticket对象
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketInRecordIdEqualTo(ticketInRecord.getId());

        ticketMapper.deleteByExample(ticketExample);


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
}
