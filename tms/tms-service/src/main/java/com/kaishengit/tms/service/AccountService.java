package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.exception.ServiceException;

/**
 *系统账号业务类接口
 * @author drm
 * @date 2018/4/12
 */
public interface AccountService {

    /**  
     *系统登录
     * @date 2018/4/12
     * @param  accountMobile 手机号, password 密码, requestIp 登录ip
     * @return 登录成功返回com.kaishengit.tms.entity.Account对象，登录失败返回null
     * @throws ServiceException 如果登录失败，则通过异常抛出具体的错误原因
     */ 
    Account login(String accountMobile,String password,String requestIp)throws ServiceException;
}
