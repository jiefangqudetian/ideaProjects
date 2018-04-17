package com.kaishengit.tms.shiro;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private AccountService accountService;
    /**
     * 判断角色与权限
     * @date 2018/4/17
     * @param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 判断登陆
     * @date 2018/4/17
     * @param authenticationToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userMobile = usernamePasswordToken.getUsername();
        if (userMobile!=null){
            Account account = accountService.findAccountByMobile(userMobile);
            if (account == null){
                throw new UnknownAccountException("找不到该账号:"+userMobile);
            } else {
                if (Account.STATE_NORMAL.equals(account.getAccountState())){

                    logger.info("{}登录成功{}",account,usernamePasswordToken.getHost());
                    //保存登录日志
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    accountLoginLog.setAccountId(account.getId());

                    accountService.saveAccountLoginLog(accountLoginLog);

                    return new SimpleAuthenticationInfo(account,account.getAccountPassword(),getName());

                } else {
                    throw new LockedAccountException("账号被禁用或锁定:"+account.getAccountState());
                }
            }
        }


        return null;
    }
}
