package com.jebms.erp.service;


import java.util.Map;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.springjdbc.DevJdbcDaoSupport;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工作日志数据EDI同步
 *
 * @author samt007@qq.com
 * @version 1.0
 */

@Service
@SuppressWarnings("rawtypes")
public class WorklogService extends DevJdbcDaoSupport {
    
	@Autowired
	WorklogService(DataSource dataSource) {
	    setDataSource(dataSource);
	}
    
	public ResultEntity headerEdi(Map<String,Object> params) throws Exception{
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
	
	public ResultEntity lineEdi(Map<String,Object> params) throws Exception{
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
	
	public ResultEntity lineContentProp(Map<String,Object> params) throws Exception{
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
