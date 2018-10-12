package com.kaishengit.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

/**
 * Shiro中的JwtToken
 * @author drm
 * @date 2018/5/18
 */
public class JwtToken implements AuthenticationToken {

    private String token;
    public JwtToken(String token){
        this.token = token;
    }



    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
