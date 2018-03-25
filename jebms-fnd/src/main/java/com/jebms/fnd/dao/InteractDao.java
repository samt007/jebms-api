package com.jebms.fnd.dao;


/**
 * 交互式报表Dao
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月7日
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jebms.comm.entity.SearchInfo;

public interface InteractDao {
	
    /**
     * 查询用户默认交互式头ID
     * @param 
     * @return
     */
    Long getDefaultIrrHid(@Param(value = "userId") long userId, @Param(value = "interactCode") String interactCode);
    
    //返回唯一行，就直接用Map就可以了。一个Map可以理解为一行！
    Map<String, Object> getIrrHeaderByHId(@Param(value = "id") long id);
    
    //可能返回多行的，只有用List
    List<Map<String, Object>> getIrrLinesByHId(@Param(value = "id") long id);
    
    void deleteIrrByHId(@Param(value = "id") long id);
    
    List<Map<String, Object>> getIrrHeaders(@Param(value = "userId") long userId, @Param(value = "interactCode") String interactCode);
    
    List<Map<String, Object>> selectForPageIrrHeaders(SearchInfo searchInfo);
    
    Long insertIrrHeader(Map<String, Object> paramMap);
    
    Long insertIrrLines(List<Map<String, Object>> paramMapList);
    
    Long updateIrrHeaderDefault(Map<String, Object> paramMap);
    
}