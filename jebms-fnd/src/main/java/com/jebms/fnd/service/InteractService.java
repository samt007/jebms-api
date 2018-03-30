package com.jebms.fnd.service;

import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.SearchInfo;

import java.util.Map;

/**
 * 交互式报表的封装业务方法
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月2日
 */
@SuppressWarnings("rawtypes")
public interface InteractService {
    
	public ResultEntity getDefaultIrr(long userId,String interactCode) ;
    
    public ResultEntity getIrr(Long id) ;
    
    public ResultEntity deleteIrr(Long id) ;
    
    public ResultEntity getIrrHeaders(Long userId, String interactCode) ;
    
    public ResultEntity selectForPageIrrHeaders(SearchInfo searchInfo) throws Exception ;
    
    public ResultEntity insertIrr(Map<String, Object> headerParamMap,String linesStr,Long userId) ;

}
