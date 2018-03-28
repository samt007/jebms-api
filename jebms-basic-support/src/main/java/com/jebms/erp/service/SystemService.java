package com.jebms.erp.service;

import com.jebms.erp.entity.FndUserVO;

/**
 * 系统管理，安全相关实体的管理类,包括用户、职责、菜单、功能，以及用户登录记录
 *
 * @author samt007@qq.com
 */

public interface SystemService {
	
    public FndUserVO selectByUsername(String username,String lang);
    
}
