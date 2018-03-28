package com.jebms.erp.service.impl;


import java.util.Date;

import javax.sql.DataSource;

import com.jebms.comm.springjdbc.DevJdbcDaoSupport;
import com.jebms.erp.entity.FndUserVO;
import com.jebms.erp.service.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统管理，安全相关实体的管理类,包括用户、职责、菜单、功能，以及用户登录记录
 *
 * @author samt007@qq.com
 */

@Service
public class SystemServiceImpl extends DevJdbcDaoSupport implements SystemService {
	
	@Autowired
	SystemServiceImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
	
    /**
     * 根据登录名获取用户
     *
     * @param username 用户名
     * @return FndUser user by user name
     */
    public FndUserVO selectByUsername(String username,String lang){
    	FndUserVO user = null;
    	try{
    		//user = userDao.selectByUserName(username);
    		user = new FndUserVO();
    		user.setUsername("WX214492");
    		user.setId(1L);
    		user.setStartDate(new Date());
    		user.setPassword("$2a$08$W2uQa1L89Dnk0sTe4S8ySuK9/y5WV3ArZSFofXCr.t.B/vrNbNJee");
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
        /*if (user == null) {
            return null;
        }*/
        //user.setRespVOs(respDao.selectListByUserId(user.getId(),lang));
        //user.setMenuVOs(getMenuListByUserId(user.getId(),lang));
        return user;
    }
    
}
