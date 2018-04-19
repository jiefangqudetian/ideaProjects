package com.kaishengit.tms.service.impl;

import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.mapper.StoreAccountMapper;
import com.kaishengit.tms.mapper.TicketStoreMapper;
import com.kaishengit.tms.service.TicketStoreService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
public class TickeStoreServiceImpl implements TicketStoreService {

    @Autowired
    private TicketStoreMapper ticketStoreMapper;
    @Autowired
    private StoreAccountMapper storeAccountMapper;


    /**
     * 创建新的售票点
     *
     * @param ticketStore
     * @return void
     * @date 2018/4/19
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveNewTicketStore(TicketStore ticketStore) {

        //1.数据库插入ticketStore对象数据，获得返回主键值
        ticketStore.setCreateTime(new Date());
        ticketStoreMapper.insertSelective(ticketStore);
        //2.创建售票点账号对象，并赋值，插入数据库
        StoreAccount storeAccount = new StoreAccount();
        storeAccount.setStoreAccount(ticketStore.getStoreTel());
          //密码默认为手机号后六位经过加密处理
        storeAccount.setStorePassword(DigestUtils.md5Hex(ticketStore.getStoreTel().substring(5)));
        storeAccount.setCreateTime(new Date());
        storeAccount.setStoreState(StoreAccount.ACCOUNT_STATE_NORMAL);
          //外键ticketStoreId为ticketStore的id
        storeAccount.setTicketStoreId(ticketStore.getId());

        storeAccountMapper.insertSelective(storeAccount);
        //3.获得storeAccount自动生成的id,并赋值给ticketStore的storeAccountId,更新数据库
        ticketStore.setStoreAccountId(storeAccount.getId());
        ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);
    }
}
