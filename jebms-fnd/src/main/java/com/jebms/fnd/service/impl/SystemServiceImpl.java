package com.jebms.fnd.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.core.Language;
import com.jebms.comm.core.Transaction;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.fnd.dao.FndFunctionDao;
import com.jebms.fnd.dao.FndFunctionTlDao;
import com.jebms.fnd.dao.FndMenuHeaderDao;
import com.jebms.fnd.dao.FndMenuHeaderTlDao;
import com.jebms.fnd.dao.FndMenuLineDao;
import com.jebms.fnd.dao.FndMenuLineTlDao;
import com.jebms.fnd.dao.FndRespDao;
import com.jebms.fnd.dao.FndRespTlDao;
import com.jebms.fnd.dao.FndUserDao;
import com.jebms.fnd.dao.FndLoginDao;
import com.jebms.fnd.dao.FndUserRespDao;
import com.jebms.fnd.entity.FndFunctionTl;
import com.jebms.fnd.entity.FndFunctionVO;
import com.jebms.fnd.entity.FndLogin;
import com.jebms.fnd.entity.FndMenuHeaderTl;
import com.jebms.fnd.entity.FndMenuHeaderVO;
import com.jebms.fnd.entity.FndMenuLineTl;
import com.jebms.fnd.entity.FndMenuLineVO;
import com.jebms.fnd.entity.FndMenuVO;
import com.jebms.fnd.entity.FndRespTl;
import com.jebms.fnd.entity.FndRespVO;
import com.jebms.fnd.entity.FndUserRespVO;
import com.jebms.fnd.entity.FndUserVO;
import com.jebms.fnd.service.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统管理，安全相关实体的管理类,包括用户、职责、菜单、功能，以及用户登录记录
 *
 * @author samt007@qq.com
 */

@Service
@SuppressWarnings("rawtypes")
public class SystemServiceImpl implements SystemService {

    @Autowired
    private FndUserDao userDao;
    
    @Autowired
    private FndUserRespDao userRespDao;
    
    @Autowired
    private FndRespDao respDao;
    
    @Autowired
    private FndRespTlDao respTlDao;
    
    @Autowired
    private FndMenuHeaderDao menuHeaderDao;
    
    @Autowired
    private FndMenuHeaderTlDao menuHeaderTlDao;
    
    @Autowired
    private FndMenuLineDao menuLineDao;
    
    @Autowired
    private FndMenuLineTlDao menuLineTlDao;
    
    @Autowired
    private FndFunctionDao functionDao;
    
    @Autowired
    private FndFunctionTlDao functionTlDao;
    
    @Autowired
    private FndLoginDao loginDao;
    
    //如果存在多语言的处理，则直接创建该成员变量即可
    private Language lang=new Language();

    /**
     * 根据登录名获取用户
     *
     * @param username 用户名
     * @return FndUser user by user name
     */
    public FndUserVO selectByUsername(String username,String lang){
    	FndUserVO user = null;
    	try{
    		user = userDao.selectByUserName(username);
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
        if (user == null) {
            return null;
        }
        user.setRespVOs(respDao.selectListByUserId(user.getId(),lang));
        user.setMenuVOs(getMenuListByUserId(user.getId(),lang));
        return user;
    }
    
    // 用户管理
	public ResultEntity selectForPageUser(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndUserVO> pageList = userDao.selectForPage(searchInfo);
        PageInfo<FndUserVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }
    
    public FndUserVO selectUserVOByPK(Long id,String lang) {
    	FndUserVO user=userDao.selectVOByPK(id);
    	user.setRespVOs(respDao.selectListByUserId(user.getId(),lang));
    	user.setValueUUID();
    	return user;
    }
    
    public ResultEntity insertUser(FndUserVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	userDao.insert(record);
		return ResultInfo.success(this.selectUserVOByPK(record.getId(),user.getLanguage()));
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateUser(FndUserVO record,AuthUser user) {
    	if(!record.equalValueUUID(userDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	userDao.updateByPrimaryKey(record);
		return ResultInfo.success(this.selectUserVOByPK(record.getId(),user.getLanguage()));
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteUser(FndUserVO record,AuthUser user) {
    	userDao.deleteByPrimaryKey(record);
		return ResultInfo.success();
    }
    
    /*
    @Override
    @Transactional(readOnly = false)
    public void updateUserPassword(FndUserVO record,AuthUser user, String newPassword) {

        SysUser user = new SysUser(userId);
        user.setPassword(newPassword);
        sysUserMapper.updatePasswordById(user);
    }*/
    
    // 用户职责管理
	public ResultEntity selectForPageUserResp(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndUserRespVO> pageList = userRespDao.selectForPage(searchInfo);
        PageInfo<FndUserRespVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }
    
    public FndUserRespVO selectUserRespVOByPK(Long id,String lang) {
    	FndUserRespVO record=userRespDao.selectVOByPK(id,lang);
    	record.setValueUUID();
    	return record;
    }
    
    public ResultEntity insertUserResp(FndUserRespVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	userRespDao.insert(record);
		return ResultInfo.success(this.selectUserRespVOByPK(record.getId(),user.getLanguage()));
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateUserResp(FndUserRespVO record,AuthUser user) {
    	if(!record.equalValueUUID(userRespDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	userRespDao.updateByPrimaryKey(record);
		return ResultInfo.success(this.selectUserRespVOByPK(record.getId(),user.getLanguage()));
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteUserResp(FndUserRespVO record,AuthUser user) {
    	userRespDao.deleteByPrimaryKey(record);
		return ResultInfo.success();
    }
    
    //Menu
    //获取用户的有效职责列表，以供前端菜单使用
    public List<FndRespVO> getRespListByUserId(Long userId,String lang) {
    	List<FndRespVO> respList=new ArrayList<>();
        for(FndRespVO resp:respDao.selectListByUserId(userId,lang)){
        	List<FndMenuVO> menuList=makeTree(getMenuListByMenuId(resp.getMenuId(),lang),false);
        	resp.setMenuVOs(menuList);
        	respList.add(resp);
        }
        return respList;
    }

    //获取用户的所有菜单，以供编辑菜单用
    public List<FndMenuVO> getMenuTree(Long userId,String lang) {
        return makeTree(getMenuListByUserId(userId,lang), false);
    }
    
    /**
     * 获得用户所有菜单列表
     * 一个用户可以分配多个职责，每个职责对应一个菜单，一个菜单下面可以有多个功能以及子菜单。
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<FndMenuVO> getMenuListByUserId(Long userId,String lang) {
        List<FndMenuVO> menuList=new ArrayList<>();
        for(FndRespVO resp:respDao.selectListByUserId(userId,lang)){
        	getMenuChildByMenuId(resp.getMenuId(),null,menuList,null,null,lang);
        }
        List<FndMenuVO> resultList=new ArrayList<>();
        sortList(resultList, menuList, "");
        return menuList;
    }
    
    public List<FndMenuVO> getMenuListByMenuId(Long menuId,String lang) {
        List<FndMenuVO> menuList=new ArrayList<>();
        getMenuChildByMenuId(menuId,null,menuList,null,null,lang);
        //System.out.println("menuList,size:"+menuList.size());
        List<FndMenuVO> resultList=new ArrayList<>();
        sortList(resultList, menuList, "");
        //System.out.println("resultList,size:"+resultList.size());
        return menuList;
    }
    
    //嵌套循环找某个菜单下的所有菜单(功能)列表
    //topMenuId:源菜单id   menuId:当前菜单id   menuChilds:所有菜单明细   level:层次(1开始)  parent:当前菜单的父级(菜单-菜单序号)
    private void getMenuChildByMenuId(Long topMenuId,Long menuId,List<FndMenuVO> menuChilds,Integer level,String parent,String lang){
    	if(menuChilds==null){
    		throw new RuntimeException("menuChild对象不允许为空！");
    	}
    	if(level==null) level=0;
    	if(menuId==null) menuId=topMenuId;
    	level++;
    	//System.out.println("topMenuId:"+topMenuId+",level:"+level);
    	for(FndMenuVO menuVO:menuLineDao.selectListByMenuId(menuId,lang)){
    		//System.out.println("-->menuVO:"+menuVO);
    		if(menuVO!=null){
        		//System.out.println("-->MenuPrompt:"+menuVO.getPrompt()+",func:"+menuVO.getFunctionName()+",subMenu:"+menuVO.getSubMenuId());
    			menuVO.setLevel(level);
        		menuVO.setTopMenuId(topMenuId);
        		menuVO.setParent(parent);
        		if(menuVO.getSubMenuId()==null){
        			menuVO.setLeaf(true);
        			menuChilds.add(menuVO);
        		}else{
        			menuVO.setLeaf(false);
        			menuChilds.add(menuVO);
        			getMenuChildByMenuId(topMenuId,menuVO.getSubMenuId(),menuChilds,level
        					,(menuVO.getMenuId()+"-"+menuVO.getMenuSequence()),lang);
        		}
    		}
    	}
    }
    
    /**
     * 菜单排序
     *
     * @param list       目标菜单列表
     * @param sourceList 原始菜单列表
     * @param parent   父级编号
     */
    private void sortList(List<FndMenuVO> list, List<FndMenuVO> sourceList, String parent) {
        sourceList.stream()
            .filter(menu -> menu.getParent() != null && menu.getParent().equals(parent))
            .forEach(menu -> {
                list.add(menu);
                // 判断是否还有子节点, 有则继续获取子节点
                sourceList.stream()
                    .filter(child -> child.getParent() != null && child.getParent().equals(menu.getMenuId()+"-"+menu.getMenuSequence()))
                    .peek(child -> sortList(list, sourceList, menu.getMenuId()+"-"+menu.getMenuSequence()))
                    .findFirst();
            });
    }
    

    /**
     * 构建树形结构
     *
     * @param originals 原始数据
     * @param useShow   使用开关控制菜单显示隐藏
     * @return 菜单列表
     */
    public List<FndMenuVO> makeTree(List<FndMenuVO> originals, boolean useShow) {
        // 构建一个Map,把所有原始数据的ID作为Key,原始数据对象作为VALUE
        Map<String, FndMenuVO> dtoMap = new HashMap<>();
        for (FndMenuVO node : originals) {
            // 原始数据对象为Node，放入dtoMap中。
            String id = node.getMenuId()+"-"+node.getMenuSequence();
            if (node.getEnabledFlag().equals("Y") || !useShow) {
                dtoMap.put(id, node);
            }
        }
        List<FndMenuVO> result = new ArrayList<>();
        for (Map.Entry<String, FndMenuVO> entry : dtoMap.entrySet()) {
        	FndMenuVO node = entry.getValue();
            String parent = node.getParent();
            if (dtoMap.get(parent) == null) {// 如果是顶层节点，直接添加到结果集合中
                result.add(node);
            } else {// 如果不是顶层节点，有父节点，然后添加到父节点的子节点中
            	FndMenuVO parentVO = dtoMap.get(parent);
            	parentVO.addChild(node);
            	parentVO.setLeaf(false);
            }
        }
        return result;
    }
    
    //新增登录信息
    public int insertLogin(FndLogin record){
    	return loginDao.insert(record);
    }
    
    public FndLogin selectLoginById(Long id){
    	return loginDao.selectByPrimaryKey(new FndLogin(id));
    }
    
    public int updateLogin(FndLogin record){
    	return loginDao.updateByPrimaryKey(record);
    }
    
    //增删改Menu头
	public ResultEntity selectForPageMenuHeader(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndMenuHeaderVO> pageList = menuHeaderDao.selectForPage(searchInfo);
        PageInfo<FndMenuHeaderVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public FndMenuHeaderVO selectMenuHeaderVOByPK(Long id,String lang) {
    	FndMenuHeaderVO ret=menuHeaderDao.selectVOByPK(id,lang);
    	ret.setValueUUID();
    	return ret;
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insertMenuHeader(FndMenuHeaderVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	if(menuHeaderDao.insert(record)==1){
    		//新增语言表
    		FndMenuHeaderTl menuHeaderTl=new FndMenuHeaderTl(record.getId(),user.getLanguage());
    		menuHeaderTl.setMenuName(record.getMenuName());
    		menuHeaderTl.setDescription(record.getDescription());
    		menuHeaderTl.setWhoInsert(user);
    		try {
				lang.insertTl(menuHeaderTlDao, menuHeaderTl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("新增语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectMenuHeaderVOByPK(record.getId(), user.getLanguage()));
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateMenuHeader(FndMenuHeaderVO record,AuthUser user) {
    	if(!record.equalValueUUID(menuHeaderDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	if(menuHeaderDao.updateByPrimaryKey(record)==1){
    		//同步更新语言表
    		FndMenuHeaderTl menuHeaderTl=new FndMenuHeaderTl(record.getId(),user.getLanguage());
    		menuHeaderTl=menuHeaderTlDao.selectByPrimaryKey(menuHeaderTl);//取数据库的记录
    		menuHeaderTl.setMenuName(record.getMenuName());
    		menuHeaderTl.setDescription(record.getDescription());
    		menuHeaderTl.setWhoUpdate(user);
    		try {
				lang.updateTl(menuHeaderTlDao, menuHeaderTl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("更新语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectMenuHeaderVOByPK(record.getId(), user.getLanguage()));
    		//Transaction.setRollbackOnly();
    		//return ResultInfo.error("测试更新回滚");
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteMenuHeader(FndMenuHeaderVO record,AuthUser user) {
    	if(menuHeaderDao.deleteByPrimaryKey(record)==1){
    		//同步删除语言表
    		FndMenuHeaderTl menuHeaderTl=new FndMenuHeaderTl(record.getId(),user.getLanguage());
    		try {
				lang.deleteTl(menuHeaderTlDao, menuHeaderTl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("更新语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }
    
    //增删改Menu行
	public ResultEntity selectForPageMenuLine(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndMenuLineVO> pageList = menuLineDao.selectForPage(searchInfo);
        PageInfo<FndMenuLineVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public FndMenuLineVO selectMenuLineVOByPK(Long menuId,Integer menuSequence,String lang) {
    	FndMenuLineVO ret=menuLineDao.selectVOByPK(menuId,menuSequence,lang);
    	ret.setValueUUID();
    	return ret;
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insertMenuLine(FndMenuLineVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	if(menuLineDao.insert(record)==1){
    		//新增语言表
    		FndMenuLineTl menuLineTl=new FndMenuLineTl(record.getMenuId(),record.getMenuSequence(),user.getLanguage());
    		menuLineTl.setPrompt(record.getPrompt());
    		menuLineTl.setDescription(record.getDescription());
    		menuLineTl.setWhoInsert(user);
    		try {
				lang.insertTl(menuLineTlDao, menuLineTl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("新增语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectMenuLineVOByPK(record.getMenuId(),record.getMenuSequence(),user.getLanguage()));
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateMenuLine(FndMenuLineVO record,AuthUser user) {
    	if(!record.equalValueUUID(menuLineDao)){//在更新前的lock检查的时候，从数据库找出5WHO对应的UUID，对比就行。
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	if(menuLineDao.updateByPrimaryKey(record)==1){
    		//同步更新语言表
    		FndMenuLineTl menuLineTl=new FndMenuLineTl(record.getMenuId(),record.getMenuSequence(),user.getLanguage());
    		menuLineTl=menuLineTlDao.selectByPrimaryKey(menuLineTl);//取数据库的记录
    		menuLineTl.setPrompt(record.getPrompt());
    		menuLineTl.setDescription(record.getDescription());
    		menuLineTl.setWhoUpdate(user);
    		try {
				lang.updateTl(menuLineTlDao, menuLineTl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				e.printStackTrace();
				return ResultInfo.error("新增语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectMenuLineVOByPK(record.getMenuId(),record.getMenuSequence(),user.getLanguage()));
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteMenuLine(FndMenuLineVO record,AuthUser user) {
    	if(menuLineDao.deleteByPrimaryKey(record)==1){
    		//同步删除语言表
    		FndMenuLineTl menuLineTl=new FndMenuLineTl(record.getMenuId(),record.getMenuSequence(),user.getLanguage());
    		try {
				lang.deleteTl(menuLineTlDao, menuLineTl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("新增语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }
    

    //增删改Function
	public ResultEntity selectForPageFunction(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndFunctionVO> pageList = functionDao.selectForPage(searchInfo);
        PageInfo<FndFunctionVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public FndFunctionVO selectFunctionVOByPK(Long functionId,String lang) {
    	FndFunctionVO ret=functionDao.selectVOByPK(functionId,lang);
    	ret.setValueUUID();
    	return ret;
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insertFunction(FndFunctionVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	if(functionDao.insert(record)==1){
    		//新增语言表
    		FndFunctionTl tl=new FndFunctionTl(record.getId(),user.getLanguage());
    		tl.setFunctionName(record.getFunctionName());
    		tl.setDescription(record.getDescription());
    		tl.setWhoInsert(user);
    		try {
				lang.insertTl(functionTlDao, tl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("新增语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectFunctionVOByPK(record.getId(),user.getLanguage()));
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateFunction(FndFunctionVO record,AuthUser user) {
    	if(!record.equalValueUUID(functionDao)){//在更新前的lock检查的时候，从数据库找出5WHO对应的UUID，对比就行。
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	if(functionDao.updateByPrimaryKey(record)==1){
    		//同步更新语言表
    		FndFunctionTl tl=new FndFunctionTl(record.getId(),user.getLanguage());
    		tl=functionTlDao.selectByPrimaryKey(tl);//取数据库的记录
    		tl.setFunctionName(record.getFunctionName());
    		tl.setDescription(record.getDescription());
    		tl.setWhoUpdate(user);
    		try {
				lang.updateTl(functionTlDao, tl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				e.printStackTrace();
				return ResultInfo.error("更新语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectFunctionVOByPK(record.getId(),user.getLanguage()));
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteFunction(FndFunctionVO record,AuthUser user) {
    	if(functionDao.deleteByPrimaryKey(record)==1){
    		//同步删除语言表
    		FndFunctionTl tl=new FndFunctionTl(record.getId(),user.getLanguage());
    		try {
				lang.deleteTl(functionTlDao, tl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("删除语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }

    //增删改Resp行
	public ResultEntity selectForPageResp(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndRespVO> pageList = respDao.selectForPage(searchInfo);
        PageInfo<FndRespVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public FndRespVO selectRespVOByPK(Long id,String lang) {    	
    	FndRespVO ret=respDao.selectVOByPK(id,lang);
    	ret.setValueUUID();
    	return ret;
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insertResp(FndRespVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	if(respDao.insert(record)==1){
    		//新增语言表
    		FndRespTl tl=new FndRespTl(record.getId(),user.getLanguage());
    		tl.setRespName(record.getRespName());
    		tl.setDescription(record.getDescription());
    		tl.setWhoInsert(user);
    		try {
				lang.insertTl(respTlDao, tl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("新增语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectRespVOByPK(record.getId(),user.getLanguage()));
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateResp(FndRespVO record,AuthUser user) {
    	if(!record.equalValueUUID(respDao)){//在更新前的lock检查的时候，从数据库找出5WHO对应的UUID，对比就行。
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	if(respDao.updateByPrimaryKey(record)==1){
    		//同步更新语言表
    		FndRespTl tl=new FndRespTl(record.getId(),user.getLanguage());
    		tl=respTlDao.selectByPrimaryKey(tl);//取数据库的记录
    		tl.setRespName(record.getRespName());
    		tl.setDescription(record.getDescription());
    		tl.setWhoUpdate(user);
    		try {
				lang.updateTl(respTlDao, tl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				e.printStackTrace();
				return ResultInfo.error("更新语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectRespVOByPK(record.getId(),user.getLanguage()));
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteResp(FndRespVO record,AuthUser user) {
    	if(respDao.deleteByPrimaryKey(record)==1){
    		//同步删除语言表
    		FndRespTl tl=new FndRespTl(record.getId(),user.getLanguage());
    		try {
				lang.deleteTl(respTlDao, tl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("删除语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }
    
}
