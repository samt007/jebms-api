package com.jebms.erp.service;


import java.util.HashMap;
import java.util.Map;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.springjdbc.DevJdbcDaoSupport;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试
 *
 * @author samt007@qq.com
 * @version 1.0
 */

@Service
@SuppressWarnings("rawtypes")
public class TestService extends DevJdbcDaoSupport {
    
	@Autowired
	TestService(DataSource dataSource) {
	    setDataSource(dataSource);
	}
	
	public ResultEntity testSql() throws Exception{
		/*String sql = "SELECT MEANING display,LOOKUP_CODE value"
				+ " FROM fnd_lookup_values  "
				+ " WHERE ROWNUM<=10 ";*/
		String sql="SELECT * FROM V$VERSION";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
    	return ResultInfo.success(this.getDevJdbcTemplate().queryForList(sql,paramMap));
    }
    
	public ResultEntity worklogHeaderEdi(Map<String,Object> params) throws Exception{
		String sql ="Declare "
				+ "     l_header_id number; "
				+ "  begin "
				+ "  XYG_ERP_WORKLOG_PKG.EDI_HEADERS( "
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
	
	public ResultEntity worklogLineEdi(Map<String,Object> params) throws Exception{
		String sql ="  begin "
				+ "  XYG_ERP_WORKLOG_PKG.EDI_LINES( "
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
}
