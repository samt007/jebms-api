package com.jebms.fnd.dao;


/**
 * 查询的Dao
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月22日
 */
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.Kanban;
import com.jebms.fnd.entity.KanbanVO;

public interface KanbanDao extends Mapper<Kanban> {
	
    /**
     * 查询VO数据
     * @param searchInfo 传入查询参数
     * @return
     */
    List<KanbanVO> selectForPage(SearchInfo searchInfo);
	
    KanbanVO selectVOByPK(@Param(value="id") Long id,@Param(value="lang") String lang );
    
    List<Map<String, Object>> selectForTop(Integer top);

}