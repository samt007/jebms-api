package com.jebms.erp.service.impl;


import java.util.HashMap;
import java.util.Map;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.springjdbc.DevJdbcDaoSupport;
import com.jebms.comm.utils.TypeConverter;
import com.jebms.erp.service.WorklogService;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 工作日志数据EDI同步
 *
 * @author samt007@qq.com
 * @version 1.0
 */

@Service(version = "1.0.0")
@org.springframework.stereotype.Service
@SuppressWarnings("rawtypes")
public class WorklogServiceImpl extends DevJdbcDaoSupport implements WorklogService {
    
	@Autowired
	WorklogServiceImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
    
	public ResultEntity headerEdi(Map<String,Object> ediParams) throws Exception{
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("P_ID", TypeConverter.type2Str(ediParams.get("id")));
    	params.put("P_DEPARTMENT_CODE", TypeConverter.type2Str(ediParams.get("departmentCode")));
    	params.put("P_WORK_GROUP_CODE", TypeConverter.type2Str(ediParams.get("workGroupCode")));
    	params.put("P_WORK_TYPE", TypeConverter.type2Str(ediParams.get("workType")));
    	params.put("P_DEF_LINE_TYPE", TypeConverter.type2Str(ediParams.get("defLineType")));
    	params.put("P_WORK_PRIORTY", TypeConverter.type2Str(ediParams.get("workPriorty")));
    	params.put("P_WORK_ITEM", TypeConverter.type2Str(ediParams.get("workItem")));
    	params.put("P_WORK_REQ_DOCUMENT", TypeConverter.type2Str(ediParams.get("workReqDocument")));
    	params.put("P_WORK_REQUEST_NUMBER", TypeConverter.type2Str(ediParams.get("workRequestNumber")));
    	params.put("P_WORK_OWNER_NUMBER", TypeConverter.type2Str(ediParams.get("workOwnerNumber")));
    	params.put("P_SCHEDULE_START_DATE", TypeConverter.str2Timestamp(TypeConverter.type2Str(ediParams.get("scheduleStartDate"))));
    	params.put("P_ACTUAL_START_DATE", TypeConverter.str2Timestamp(TypeConverter.type2Str(ediParams.get("actualStartDate"))));
    	params.put("P_SCHEDULE_FINISH_DATE", TypeConverter.str2Timestamp(TypeConverter.type2Str(ediParams.get("scheduleFinishDate"))));
    	params.put("P_WORK_SPEND_HOURS", TypeConverter.type2Str(ediParams.get("workSpendHours")));
    	params.put("P_WORK_LOG", TypeConverter.type2Str(ediParams.get("workLog")));
    	params.put("P_STATUS", TypeConverter.type2Str(ediParams.get("status")));
    	params.put("P_STATUS_WT_DATE", TypeConverter.str2Timestamp(TypeConverter.type2Str(ediParams.get("statusWtDate"))));
    	params.put("P_STATUS_WT_NUMBER", TypeConverter.type2Str(ediParams.get("statusWtNumber")));
    	params.put("P_CANCEL_DATE", TypeConverter.str2Timestamp(TypeConverter.type2Str(ediParams.get("cancelDate"))));
    	params.put("P_CANCEL_USER_NUMBER", TypeConverter.type2Str(ediParams.get("cancelUserNumber")));
    	params.put("P_ACTUAL_FINISH_DATE", TypeConverter.str2Timestamp(TypeConverter.type2Str(ediParams.get("actualFinishDate"))));
    	params.put("P_FINISH_USER_NUMBER", TypeConverter.type2Str(ediParams.get("finishUserNumber")));
    	params.put("P_DESCRIPTION", TypeConverter.type2Str(ediParams.get("description")));
    	//params.put("P_HEADER_ID", TypeConverter.type2Str(ediParams.get("headerId")));
    	params.put("P_EDI_TYPE", TypeConverter.type2Str(ediParams.get("ediType")));
    	params.put("P_EDI_EMP_NUMBER", TypeConverter.type2Str(ediParams.get("ediEmpNumber")));
    	params.put(ResultEntity.PARAM1, TypeConverter.type2Str(ediParams.get("erpHeaderId")));
		String sql ="Declare "
				+ "     l_header_id number; "
				+ "  begin "
				+ "  APPS.XYG_ERP_WORKLOG_PKG.EDI_HEADERS( "
				+ "  :P_ID"
				+ " ,:P_DEPARTMENT_CODE"
				+ " ,:P_WORK_GROUP_CODE"
                + " ,:P_WORK_TYPE"
                + " ,:P_DEF_LINE_TYPE"
                + " ,:P_WORK_PRIORTY"
                + " ,:P_WORK_ITEM"
                + " ,:P_WORK_REQ_DOCUMENT"
                + " ,:P_WORK_REQUEST_NUMBER"
                + " ,:P_WORK_OWNER_NUMBER"
                + " ,:P_SCHEDULE_START_DATE"
                + " ,:P_ACTUAL_START_DATE"
                + " ,:P_SCHEDULE_FINISH_DATE"
                + " ,:P_WORK_SPEND_HOURS"
                + " ,:P_WORK_LOG"
                + " ,:P_STATUS"
                + " ,:P_STATUS_WT_DATE"
                + " ,:P_STATUS_WT_NUMBER"
                + " ,:P_CANCEL_DATE"
                + " ,:P_CANCEL_USER_NUMBER"
                + " ,:P_ACTUAL_FINISH_DATE"
                + " ,:P_FINISH_USER_NUMBER"
                + " ,:P_DESCRIPTION"
                + " ,:P_EDI_TYPE"
                + " ,:P_EDI_EMP_NUMBER"
                + " ,:"+ResultEntity.PARAM1
				+ " ,:"+ResultEntity.CODE
				+ " ,:"+ResultEntity.MESSAGE
				+ " ); "
				+ "end;";
		return this.getDevJdbcTemplate().executeForResultEntity(sql, params);
    }
	
	public ResultEntity lineEdi(Map<String,Object> ediParams) throws Exception{
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("P_ID", TypeConverter.type2Str(ediParams.get("id")));
    	params.put("P_HEADER_ID", TypeConverter.type2Str(ediParams.get("headerId")));
    	params.put("P_LINE_NUM", TypeConverter.type2Str(ediParams.get("lineNum")));
    	params.put("P_LINE_TYPE", TypeConverter.type2Str(ediParams.get("lineType")));
    	params.put("P_LINE_SUB_TYPE", TypeConverter.type2Str(ediParams.get("lineSubType")));
    	params.put("P_LINE_CONTENT", TypeConverter.type2Str(ediParams.get("lineContent")));
    	params.put("P_LINE_START_DATE", TypeConverter.str2Timestamp(TypeConverter.type2Str(ediParams.get("lineStartDate"))));
    	params.put("P_LINE_FINISH_DATE", TypeConverter.str2Timestamp(TypeConverter.type2Str(ediParams.get("lineFinishDate"))));
    	params.put("P_APPLICATION_SHORT_NAME", TypeConverter.type2Str(ediParams.get("applicationShortName")));
    	params.put("P_LANGUAGE", TypeConverter.type2Str(ediParams.get("language")));
    	params.put("P_DESCRIPTION", TypeConverter.type2Str(ediParams.get("description")));
    	params.put("P_EDI_TYPE", TypeConverter.type2Str(ediParams.get("ediType")));
    	params.put("P_EDI_EMP_NUMBER", TypeConverter.type2Str(ediParams.get("ediEmpNumber")));
    	params.put("P_ERP_HEADER_ID", TypeConverter.type2Str(ediParams.get("erpHeaderId")));
    	params.put(ResultEntity.PARAM1, TypeConverter.type2Str(ediParams.get("erpLineId")));
		String sql ="  begin "
				+ "  APPS.XYG_ERP_WORKLOG_PKG.EDI_LINES( "
				+ "  :P_ID"
				+ " ,:P_HEADER_ID"
				+ " ,:P_LINE_NUM"
                + " ,:P_LINE_TYPE"
                + " ,:P_LINE_SUB_TYPE"
                + " ,:P_LINE_CONTENT"
                + " ,:P_LINE_START_DATE"
                + " ,:P_LINE_FINISH_DATE"
                + " ,:P_APPLICATION_SHORT_NAME"
                + " ,:P_LANGUAGE"
                + " ,:P_DESCRIPTION"
                + " ,:P_EDI_TYPE"
                + " ,:P_EDI_EMP_NUMBER"
                + " ,:P_ERP_HEADER_ID"
                + " ,:"+ResultEntity.PARAM1
				+ " ,:"+ResultEntity.CODE
				+ " ,:"+ResultEntity.MESSAGE
				+ " ); "
				+ "end;";
		return this.getDevJdbcTemplate().executeForResultEntity(sql, params);
    }
	
	public ResultEntity lineContentProp(Map<String,Object> ediParams) throws Exception{
    	Map<String,Object> params=new HashMap<String,Object>();
    	params.put("P_LINE_CONTENT", TypeConverter.type2Str(ediParams.get("lineContent")));
		String sql =" declare"
				+ " L_LINE_CONTENT VARCHAR2(50); "
				+ " L_LINE_SUB_TYPE VARCHAR2(50);"
				+ " L_APPLICATION_SHORT_NAME VARCHAR2(50); "
				+ " begin "
				+ " L_LINE_CONTENT := :P_LINE_CONTENT; "
				+ " L_LINE_SUB_TYPE := APPS.XYG_ERP_WORKLOG_PKG.GET_DEF_LINE_SUB_TYPE(L_LINE_CONTENT,0); "
				+ " L_APPLICATION_SHORT_NAME := APPS.XYG_ERP_WORKLOG_PKG.GET_DEF_APP_SHORT_NAME(L_LINE_CONTENT,L_LINE_SUB_TYPE,0);"
                + " :"+ResultEntity.PARAM1+":= L_LINE_SUB_TYPE; "
                + " :"+ResultEntity.PARAM2+":= L_APPLICATION_SHORT_NAME; "
                + " :"+ResultEntity.CODE+":= 0; "
                + " :"+ResultEntity.MESSAGE+":= ''; "
				+ "end;";
		return this.getDevJdbcTemplate().executeForResultEntity(sql, params);
    }
}
