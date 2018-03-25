package com.jebms.comm.security.util;

import com.google.gson.Gson;
import com.jebms.comm.security.AbstractTokenUtil;
import com.jebms.comm.security.model.AuthUser;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * The type Token util.
 *
 * @author samt007@qq.com
 */
@Component
@ConfigurationProperties("security.jwt")
public class TokenUtil extends AbstractTokenUtil {

    @Override
    public UserDetails getUserDetails(String token) {

        String userDetailsString = getUserDetailsString( WebUtils.getRequest(),token);
        if (userDetailsString != null) {
            return (UserDetails) new Gson().fromJson(userDetailsString, AuthUser.class);
            //return new Gson().fromJson(userDetailsString, AuthUser.class);
        }
        return null;
    }

}