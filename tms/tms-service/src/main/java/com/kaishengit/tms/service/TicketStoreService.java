package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.TicketStore;

/**
 * 年票售票点业务类
 * @author drm
 * @date 2018/4/19
 */
public interface TicketStoreService {

    /**  
     * 创建新的售票点
     * @date 2018/4/19
     * @param [ticketStore]  
     * @return void  
     */ 
    void saveNewTicketStore(TicketStore ticketStore);
}
