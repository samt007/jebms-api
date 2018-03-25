package com.jebms.fnd.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jebms.fnd.service.SystemService;

/**
 * 系统管理，安全相关实体的管理类,包括用户、职责、菜单、功能.
 *
 * @author samt007@qq.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemServiceTest {
	
    @Autowired
    private SystemService systemService;
    
    @Test
    public void testGetMenuList(){
    	/*List<FndMenuVO> menuList=systemService.getMenuListByMenuId(1L);
    	for(FndMenuVO menu:menuList){
    		System.out.println("level:"+menu.getLevel()+",topMenu:"+menu.getTopMenuId()
    				+":parentMenu:"+menu.getParent()
    				+":currMenu:"+menu.getMenuId()+"-->seq:"+menu.getMenuSequence()
    				+",menuPrompt:"+menu.getPrompt()+"(subMenu:"+menu.getSubMenuId()
    				+",func:"+menu.getFunctionName()+",permi:"+menu.getPermission()+")");
    	}*/
    }
    
    @Test
    public void testUser(){
    	System.out.println("systemService:"+systemService);
    	//FndUserVO user=systemService.selectByUserName("admin");
    }
    
    public static void main(String[] args){
    	SystemServiceTest test=new SystemServiceTest();
    	//test.testGetMenuList();
    	test.testUser();
    }
}
