package com.jebms.fnd.service;


import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.Kanban;
import com.jebms.fnd.entity.KanbanVO;

/**
 * 业务方法
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月2日
 */
@SuppressWarnings("rawtypes")
public interface KanbanService  {

    /**
     * 查询分页信息
     * @param searchInfo 传入当前分页数 和 搜索信息
     * @return
     * @throws Exception 
     */
	public ResultEntity selectForPage(SearchInfo searchInfo) throws Exception ;

    public KanbanVO selectVOByPK(Long id,String lang) ;
    
    public String selectForTop() ;
    
    //获取总看板的成交客户
    public Long getKanbanCustomerCnt();
	
    public ResultEntity insert(Kanban record) ;
    
	public ResultEntity update(Kanban record) ;

    public ResultEntity delete(Kanban record) ;

}
