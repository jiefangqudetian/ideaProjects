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
    Map<String,Object> countTicketByState();

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
}
