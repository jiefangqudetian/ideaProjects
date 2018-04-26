package com.kaishengit.tms.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketInRecord;
import com.kaishengit.tms.entity.TicketOutRecord;

import java.util.List;
import java.util.Map;

/**
 * 年票业务类
 * @author drm
 * @date 2018/4/19
 */
public interface TicketService {
    /**
     * 保存一个入库记录
     * @date 2018/4/21
     * @param [ticket]
     * @return void
     */
    void saveTicketInRecord(TicketInRecord ticketInRecord);

    /**
     * 查找所有入库记录
     * @date 2018/4/21
     * @param []
     * @return java.util.List<com.kaishengit.tms.entity.TicketInRecord>
     */
    List<TicketInRecord> findAllTicketInRecord();

    /**
     * 根据id删除入库记录
     * @date 2018/4/21
     * @param [id]
     * @return void
     */
    void delTicketInRecordById(Integer id);

    TicketInRecord findTicketInRecordById(Integer id);

    /**
     * 修改入库记录
     * @date 2018/4/21
     * @param [ticketInRecord]
     * @return void
     */
    void updateTicketInRecord(TicketInRecord ticketInRecord);

    /**
     * 根据页码查询入库记录
     * @date 2018/4/23
     * @param [pageNo]
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketInRecord>
     */
    PageInfo<TicketInRecord> findTicketRecordByPageNo(Integer pageNo);

    /**
     * 根据年票状态统计年票数量
     * @date 2018/4/23
     * @param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String,Long> countTicketByState();

    /**
     * 根据页码查询下发记录
     * @date 2018/4/23
     * @param [pageNo]
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketOutRecord>
     */
    PageInfo<TicketOutRecord> findTicketOutRecordByPageNo(Integer pageNo);

    /**
     * 新增下发记录
     * @date 2018/4/23
     * @param [ticketOutRecord]
     * @return void
     */
    void saveTicketOutRecord(TicketOutRecord ticketOutRecord);

    /**
     * 删除下发单
     * @date 2018/4/23
     * @param [id]
     * @return void
     */
    void delTicketOutRecordById(Integer id);

    /**
     * 根据当前页号和查询参数查询下发列表
     * @date 2018/4/24
     * @param [pageNO, queryParam]
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketOutRecord>
     */
    PageInfo<TicketOutRecord> findTicketOutRecordByPageNoAndQueryParam(Integer pageNO, Map<String, Object> queryParam);

    /**
     * 根据ID查找对应下发单
     * @date 2018/4/24
     * @param [id]
     * @return com.kaishengit.tms.entity.TicketOutRecord
     */
    TicketOutRecord findTicketOutRecordById(Integer id);

    /**  
     * 根据ID对对应的售票单进行支付-财务结算
     * @date 2018/4/24
     * @param [id, payType]  
     * @return void  
     */ 
    void payTicketOutRecord(Integer id, String payType);
}
