package com.jebms.fnd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.core.Transaction;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.comm.springjdbc.DevJdbcDaoSupport;
import com.jebms.comm.utils.HttpUtils;
import com.jebms.comm.utils.IPUtils;
import com.jebms.comm.utils.SqlUtil;
import com.jebms.comm.utils.TypeConverter;
import com.jebms.fnd.dao.FndWorklogHeaderDao;
import com.jebms.fnd.dao.FndWorklogLineDao;
import com.jebms.fnd.entity.FndWorklogHeaderVO;
import com.jebms.fnd.entity.FndWorklogLineVO;
import com.jebms.fnd.service.FndWorklogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Worklog工作日志表的Service封装。是一个非常经典的Master-Detail模型Service
 *
 * @author samt007@qq.com
 */

@Service
@SuppressWarnings("rawtypes")
public class FndWorklogServiceImpl  extends DevJdbcDaoSupport implements FndWorklogService {
    
    @Autowired
    private FndWorklogHeaderDao headerDao;
    
    @Autowired
    private FndWorklogLineDao lineDao;
    
	@Autowired
	FndWorklogServiceImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
	
	@Value("${global.edi.erpEdi}")
	private String erpEdi;
    
    //增删改头
	public ResultEntity selectForPageHeader(SearchInfo searchInfo) throws Exception {
    	StringBuffer sqlConditionBuf=new StringBuffer();
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"fwh.work_item","workItem"));
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"fwh.work_owner_pid","workOwnerPid"));
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"fwh.actual_start_date","actualStartDateF","actualStartDateT"));
    	searchInfo.setSqlCondition(sqlConditionBuf.toString());
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndWorklogHeaderVO> pageList = headerDao.selectForPage(searchInfo);
        PageInfo<FndWorklogHeaderVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public FndWorklogHeaderVO selectHeaderVOByPK(Long id,String lang) {
    	FndWorklogHeaderVO record=headerDao.selectVOByPK(id,lang);
    	record.setValueUUID();
    	return record;
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insertHeader(FndWorklogHeaderVO record,AuthUser user, HttpServletRequest request) {
    	record.setWhoInsert(user);
    	if(headerDao.insert(record)==1){
    		record=this.selectHeaderVOByPK(record.getId(),user.getLanguage());
    		if(TypeConverter.isNotEmpty(erpEdi)){
        		try {
        			ediHeaders("INSERT",record,user,request);
        		} catch (Exception e) {
        			Transaction.setRollbackOnly();
        			return ResultInfo.error("数据同步到ERP失败！信息:"+e.getMessage());
        		}
    		}
    		return ResultInfo.success(record);
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    public void ediHeaders(String ediType, FndWorklogHeaderVO record, AuthUser user, HttpServletRequest request) throws Exception{
		if(!ediType.equals("INSERT")&&TypeConverter.isEmpty(record.getErpHeaderId())){
			System.out.println("erpHeaderId 为空！不需要同步到ERP！");
			return;
		}
		JSONObject param=(JSONObject) JSON.toJSON(record);
		param.put("ediType", ediType);
		param.put("ediEmpNumber", user.getEmpNumber());
		// 自动edi同步到erp系统
		Map<String,String> reqHeaders=new HashMap<String,String>();
		reqHeaders.put("Content-Type", "application/json; charset=utf-8"); //解决返回415的问题
		reqHeaders.put("Authorization", request.getHeader("Authorization")); 
		reqHeaders.put("X-Forwarded-For", IPUtils.getIpAddr(request)); 
		JSONObject resp=HttpUtils.httpPost(erpEdi+"/jebms/erp/worklog/headerEdi", param, reqHeaders);
		if(!resp.get("code").equals("0")){
			throw new RuntimeException("EDI同步日志信息到ERP失败！错误信息："+resp.get("message"));
		}
		if(ediType.equals("INSERT")){
			String sql = " UPDATE fnd_worklog_headers SET ERP_HEADER_ID=:1  WHERE id = :2 ";
			Map<String,Object> paramMap=new  HashMap<String,Object>();
			paramMap.put("1", resp.getString("param1"));
			paramMap.put("2", record.getId());
			this.getDevJdbcTemplate().update(sql, paramMap);
			record.setErpHeaderId(Long.parseLong(resp.getString("param1")));
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateHeader(FndWorklogHeaderVO record,AuthUser user,HttpServletRequest request) {
    	/*if(!record.equalValueUUID(headerDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}*/
    	//FndWorklogHeader h = headerDao.selectByPrimaryKey(record);
    	record.setWhoUpdate(user);
    	if(headerDao.updateByPrimaryKey(record)==1){
    		record=this.selectHeaderVOByPK(record.getId(),user.getLanguage());
    		if(TypeConverter.isNotEmpty(erpEdi)){
        		try {
        			ediHeaders("UPDATE",record,user,request);
        		} catch (Exception e) {
        			Transaction.setRollbackOnly();
        			return ResultInfo.error("数据同步到ERP失败！信息:"+e.getMessage());
        		}
    		}
    		return ResultInfo.success(record);
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteHeader(FndWorklogHeaderVO record,AuthUser user,HttpServletRequest request) {
    	if(headerDao.deleteByPrimaryKey(record)==1){
    		if(TypeConverter.isNotEmpty(erpEdi)){
        		try {
        			ediHeaders("DELETE",record,user,request);
        		} catch (Exception e) {
        			Transaction.setRollbackOnly();
        			return ResultInfo.error("数据同步到ERP失败！信息:"+e.getMessage());
        		}
    		}
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }
    
    //增删改行
	public ResultEntity selectForPageLine(SearchInfo searchInfo) throws Exception {
    	StringBuffer sqlConditionBuf=new StringBuffer();
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"fwl.header_id","headerId"));
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"fwl.line_content","lineContent"));
    	//sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"bk.start_date","startDateF","startDateT"));
    	searchInfo.setSqlCondition(sqlConditionBuf.toString());
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndWorklogLineVO> pageList = lineDao.selectForPage(searchInfo);
        PageInfo<FndWorklogLineVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public FndWorklogLineVO selectLineVOByPK(Long id,String lang) {
    	FndWorklogLineVO record=lineDao.selectVOByPK(id,lang);
    	record.setValueUUID();
    	return record;
    }
    
    public void ediLines(String ediType, FndWorklogLineVO record, AuthUser user, HttpServletRequest request) throws Exception{
		JSONObject param=(JSONObject) JSON.toJSON(record);
		param.put("ediType", ediType);
		param.put("ediEmpNumber", user.getEmpNumber());
		Map<String,Object> hidParam=new  HashMap<String,Object>();
		hidParam.put("1", record.getHeaderId());
		//System.out.println("record.getHeaderId():"+record.getHeaderId());
		String erpHeaderId=TypeConverter.type2Str(this.getDevJdbcTemplate().queryForObjSingle("select erp_header_id from fnd_worklog_headers where id=:1", hidParam));
		if(TypeConverter.isEmpty(erpHeaderId)){
			System.out.println("erpHeaderId 为空！行不需要同步到ERP！");
			return;
		}
		param.put("erpHeaderId", erpHeaderId);
		// 自动edi同步到erp系统
		Map<String,String> reqHeaders=new HashMap<String,String>();
		reqHeaders.put("Content-Type", "application/json; charset=utf-8"); //解决返回415的问题
		reqHeaders.put("Authorization", request.getHeader("Authorization")); 
		reqHeaders.put("X-Forwarded-For", IPUtils.getIpAddr(request)); 
		JSONObject resp=HttpUtils.httpPost(erpEdi+"/jebms/erp/worklog/lineEdi", param, reqHeaders);
		if(!resp.get("code").equals("0")){
			throw new RuntimeException("EDI同步日志行信息到ERP失败！错误信息："+resp.get("message"));
		}
		if(ediType.equals("INSERT")){
			String sql = " UPDATE fnd_worklog_lines  SET ERP_LINE_ID=:1 WHERE id = :2 ";
			Map<String,Object> paramMap=new  HashMap<String,Object>();
			paramMap.put("1", resp.getString("param1"));
			paramMap.put("2", record.getId());
			this.getDevJdbcTemplate().update(sql, paramMap);
			record.setErpLineId(Long.parseLong(resp.getString("param1")));
		}
    }
    
    public ResultEntity getLineContentProp(String lineContent, HttpServletRequest request) throws Exception{
    	if(TypeConverter.isEmpty(erpEdi)) return ResultInfo.success();
		JSONObject param=new JSONObject();
		param.put("lineContent", lineContent);
		// 自动edi同步到erp系统
		Map<String,String> reqHeaders=new HashMap<String,String>();
		reqHeaders.put("Content-Type", "application/json; charset=utf-8"); //解决返回415的问题
		reqHeaders.put("Authorization", request.getHeader("Authorization")); 
		reqHeaders.put("X-Forwarded-For", IPUtils.getIpAddr(request)); 
		JSONObject resp=HttpUtils.httpPost(erpEdi+"/jebms/erp/worklog/lineContentProp", param, reqHeaders);
		if(!resp.get("code").equals("0")){
			throw new RuntimeException("获取行内容信息失败！错误信息："+resp.get("message"));
		}
		Map<String,String> ret=new HashMap<String,String>();
		ret.put("lineSubType", resp.getString("param1"));
		ret.put("applicationShortName", resp.getString("param2"));
		return ResultInfo.success(ret);
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insertLine(FndWorklogLineVO record,AuthUser user,HttpServletRequest request) {
    	record.setWhoInsert(user);
    	if(lineDao.insert(record)==1){
    		record=this.selectLineVOByPK(record.getId(),user.getLanguage());
    		if(TypeConverter.isNotEmpty(erpEdi)){
        		try {
        			ediLines("INSERT",record,user,request);
        		} catch (Exception e) {
        			Transaction.setRollbackOnly();
        			return ResultInfo.error("数据同步到ERP失败！信息:"+e.getMessage());
        		}
    		}
    		return ResultInfo.success(record);
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateLine(FndWorklogLineVO record,AuthUser user,HttpServletRequest request) {
    	if(!record.equalValueUUID(lineDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	if(lineDao.updateByPrimaryKey(record)==1){
    		record=this.selectLineVOByPK(record.getId(),user.getLanguage());
    		if(TypeConverter.isNotEmpty(erpEdi)){
        		try {
        			ediLines("UPDATE",record,user,request);
        		} catch (Exception e) {
        			Transaction.setRollbackOnly();
        			return ResultInfo.error("数据同步到ERP失败！信息:"+e.getMessage());
        		}
    		}
    		return ResultInfo.success(record);
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteLine(FndWorklogLineVO record,AuthUser user,HttpServletRequest request) {
    	if(lineDao.deleteByPrimaryKey(record)==1){
    		if(TypeConverter.isNotEmpty(erpEdi)){
        		try {
        			ediLines("DELETE",record,user,request);
        		} catch (Exception e) {
        			Transaction.setRollbackOnly();
        			return ResultInfo.error("数据同步到ERP失败！信息:"+e.getMessage());
        		}
    		}
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }
    
    // 自动从数据库找对应的日志的最大行号
    public ResultEntity getMaxLineNum(Long headerId) throws Exception {
		String sql = "SELECT ifnull(max(line_num),0)"
				+ " FROM fnd_worklog_lines  "
				+ " WHERE header_id = :1 ";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", headerId);
		return ResultInfo.success((Long)this.getDevJdbcTemplate().queryForObjSingle(sql, paramMap));
    }
    
    public ResultEntity getPersonDepartment(Long personId) throws Exception {
		String sql = "select pd.department_name display,pd.id value "
				+ "  from per_departments pd  "
				+ " where exists( "
				+ "   select 1 "
				+ "     from per_workgroups pw,per_workgroup_emps pwe "
				+ "    where pw.department_id = pd.id "
				+ "      and pw.id = pwe.work_group_id "
				+ "      and pwe.person_id = :1) "
				+ "  order by 1 ";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", personId);
		return ResultInfo.success(this.getDevJdbcTemplate().queryForList(sql, paramMap));
    }
    
    public ResultEntity getPersonWorkGroup(Long departmentId,Long personId) throws Exception {
		String sql = "select pw.work_group_name display,pw.id value "
				+ "  from per_departments pd  "
				+ "      ,per_workgroups pw "
				+ " where pw.department_id = pd.id "
				+ "   and pd.id = :1 "
				+ "   and exists( "
				+ "    select 1 "
				+ "      from per_workgroup_emps pwe "
				+ "    where pw.id = pwe.work_group_id "
				+ "      and pwe.person_id = :2) "
				+ "  order by 1 ";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", departmentId);
		paramMap.put("2", personId);
		//Thread.sleep(2000);
		return ResultInfo.success(this.getDevJdbcTemplate().queryForList(sql, paramMap));
    }
    
}
