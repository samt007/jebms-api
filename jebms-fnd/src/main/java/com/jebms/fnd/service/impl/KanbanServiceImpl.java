package com.jebms.fnd.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.utils.SqlUtil;
import com.jebms.comm.core.BaseService;
import com.jebms.fnd.dao.KanbanDao;
import com.jebms.fnd.entity.Kanban;
import com.jebms.fnd.entity.KanbanVO;
import com.jebms.fnd.service.KanbanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 * 业务方法
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月2日
 */
@Service
@SuppressWarnings("rawtypes")
public class KanbanServiceImpl extends BaseService<KanbanDao,Kanban> implements KanbanService {

    @Autowired
    private KanbanDao kanbanDao;
    
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 查询分页信息
     * @param searchInfo 传入当前分页数 和 搜索信息
     * @return
     * @throws Exception 
     */
	public ResultEntity selectForPage(SearchInfo searchInfo) throws Exception {
    	//首先处理复杂的查询条件。如果还有更加特殊的，直接Append进来即可。
    	StringBuffer sqlConditionBuf=new StringBuffer();
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"customer_name","customerName"));
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"bk.start_date","startDateF","startDateT"));
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"bk.currency","currency"));
    	if(searchInfo.getConditionMap().get("amountFlag")!=null&&searchInfo.getConditionMap().get("amountFlag").equals("Y")){
    		sqlConditionBuf.append(" AND bk.amount>=( "
    				+ " SELECT sh.meaning FROM fnd_lookup_values sh "
    				+ " WHERE sh.lookup_type='KANBAN_AMOUNT_SHOW' "
    				+ " AND sh.language = #{authUser.language}) ");
    	}
        //System.out.println("sqlConditionBuf:"+sqlConditionBuf.toString());
    	searchInfo.setSqlCondition(sqlConditionBuf.toString());
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<KanbanVO> pageList = kanbanDao.selectForPage(searchInfo);
        //System.out.println("pageList Size is:"+pageList.size());
        PageInfo<KanbanVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public KanbanVO selectVOByPK(Long id,String lang) {
    	KanbanVO ret=kanbanDao.selectVOByPK(id,lang);
    	ret.setValueUUID();
        return ret;
    }
    
    public String selectForTop() {
    	List<Map<String, Object>> kanbanTopList = kanbanDao.selectForTop(10);
    	StringBuffer sb=new StringBuffer();
    	DecimalFormat myformat = new DecimalFormat();
    	myformat.applyPattern("###,###,###");
    	for(int i=0;i<kanbanTopList.size();i++){
    		sb.append("第"+(i+1)+"名:"+kanbanTopList.get(i).get("customerName")+"(¥"+myformat.format(kanbanTopList.get(i).get("tolAmount"))+"元); ");
    	}
    	//System.out.println(sb.toString());
        return sb.toString();
    }
    
    //获取总看板的成交客户
    public Long getKanbanCustomerCnt(){
    	return jdbcTemplate.queryForObject("SELECT count(DISTINCT customer_id) FROM bw_kanban", Long.class);
    }

}
