package com.jebms.fnd.service;

import java.util.List;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.fnd.entity.FndFunctionVO;
import com.jebms.fnd.entity.FndLogin;
import com.jebms.fnd.entity.FndMenuHeaderVO;
import com.jebms.fnd.entity.FndMenuLineVO;
import com.jebms.fnd.entity.FndMenuVO;
import com.jebms.fnd.entity.FndRespVO;
import com.jebms.fnd.entity.FndUserRespVO;
import com.jebms.fnd.entity.FndUserVO;

/**
 * 系统管理，安全相关实体的管理类,包括用户、职责、菜单、功能，以及用户登录记录
 *
 * @author samt007@qq.com
 */

@SuppressWarnings("rawtypes")
public interface SystemService {

    /**
     * 根据登录名获取用户
     *
     * @param username 用户名
     * @return FndUser user by user name
     */
    public FndUserVO selectByUsername(String username,String lang);
    
    // 用户管理
	public ResultEntity selectForPageUser(SearchInfo searchInfo) throws Exception ;
    
    public FndUserVO selectUserVOByPK(Long id,String lang) ;
    
    public ResultEntity insertUser(FndUserVO record,AuthUser user) ;
    
    public ResultEntity updateUser(FndUserVO record,AuthUser user) ;
    
    public ResultEntity deleteUser(FndUserVO record,AuthUser user) ;
    
    // 用户职责管理
	public ResultEntity selectForPageUserResp(SearchInfo searchInfo) throws Exception ;
    
    public FndUserRespVO selectUserRespVOByPK(Long id,String lang) ;
    
    public ResultEntity insertUserResp(FndUserRespVO record,AuthUser user) ;
    
    public ResultEntity updateUserResp(FndUserRespVO record,AuthUser user) ;
    
    public ResultEntity deleteUserResp(FndUserRespVO record,AuthUser user) ;
    
    //Menu
    //获取用户的有效职责列表，以供前端菜单使用
    public List<FndRespVO> getRespListByUserId(Long userId,String lang) ;

    //获取用户的所有菜单，以供编辑菜单用
    public List<FndMenuVO> getMenuTree(Long userId,String lang) ;
    
    /**
     * 获得用户所有菜单列表
     * 一个用户可以分配多个职责，每个职责对应一个菜单，一个菜单下面可以有多个功能以及子菜单。
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<FndMenuVO> getMenuListByUserId(Long userId,String lang) ;
    
    public List<FndMenuVO> getMenuListByMenuId(Long menuId,String lang) ;
    
    /**
     * 构建树形结构
     *
     * @param originals 原始数据
     * @param useShow   使用开关控制菜单显示隐藏
     * @return 菜单列表
     */
    public List<FndMenuVO> makeTree(List<FndMenuVO> originals, boolean useShow) ;
    
    //新增登录信息
    public int insertLogin(FndLogin record);
    
    public FndLogin selectLoginById(Long id);
    
    public int updateLogin(FndLogin record);
    
    //增删改Menu头
	public ResultEntity selectForPageMenuHeader(SearchInfo searchInfo) throws Exception;

    public FndMenuHeaderVO selectMenuHeaderVOByPK(Long id,String lang);
    
    public ResultEntity insertMenuHeader(FndMenuHeaderVO record,AuthUser user) ;
    
    public ResultEntity updateMenuHeader(FndMenuHeaderVO record,AuthUser user) ;
    
    public ResultEntity deleteMenuHeader(FndMenuHeaderVO record,AuthUser user);
    
    //增删改Menu行
	public ResultEntity selectForPageMenuLine(SearchInfo searchInfo) throws Exception;

    public FndMenuLineVO selectMenuLineVOByPK(Long menuId,Integer menuSequence,String lang);
    
    public ResultEntity insertMenuLine(FndMenuLineVO record,AuthUser user) ;
    
    public ResultEntity updateMenuLine(FndMenuLineVO record,AuthUser user) ;
    
    public ResultEntity deleteMenuLine(FndMenuLineVO record,AuthUser user) ;
    
    //增删改Function
	public ResultEntity selectForPageFunction(SearchInfo searchInfo) throws Exception;

    public FndFunctionVO selectFunctionVOByPK(Long functionId,String lang) ;
    
    public ResultEntity insertFunction(FndFunctionVO record,AuthUser user);
    
    public ResultEntity updateFunction(FndFunctionVO record,AuthUser user);
    
    public ResultEntity deleteFunction(FndFunctionVO record,AuthUser user);

    //增删改Resp行
	public ResultEntity selectForPageResp(SearchInfo searchInfo) throws Exception;

    public FndRespVO selectRespVOByPK(Long id,String lang) ;
    
    public ResultEntity insertResp(FndRespVO record,AuthUser user);
    
    public ResultEntity updateResp(FndRespVO record,AuthUser user) ;
    
    public ResultEntity deleteResp(FndRespVO record,AuthUser user);
    
}
