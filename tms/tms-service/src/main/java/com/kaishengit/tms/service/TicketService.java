package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.TicketInRecord;

import java.util.List;

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
}
