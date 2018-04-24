package com.jebms.fnd.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.utils.TypeConverter;
import com.jebms.fnd.dao.InteractDao;
import com.jebms.fnd.service.InteractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 交互式报表的封装业务方法
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月2日
 */
@Service
@SuppressWarnings("rawtypes")
public class InteractServiceImpl implements InteractService {

    @Autowired
    private InteractDao irrDao;
    
	public ResultEntity getDefaultIrr(long userId,String interactCode) {
    	Long defHid=irrDao.getDefaultIrrHid(userId, interactCode);
		Map<String,Object> defIrr=new HashMap<String,Object>();
    	if (TypeConverter.isNotEmpty(defHid)){
    		defIrr.put("exists", "Y");
    		defIrr.put("header", irrDao.getIrrHeaderByHId(defHid));
    		defIrr.put("lines", irrDao.getIrrLinesByHId(defHid));
    	}else{
    		defIrr.put("exists", "N");
    		defIrr.put("header", null);
    		defIrr.put("lines", null);
    	}
    	return ResultInfo.success(defIrr);
    }
    
    public ResultEntity getIrr(Long id) {
		Map<String,Object> irr=new HashMap<String,Object>();
		irr.put("exists", "Y");
		irr.put("header", irrDao.getIrrHeaderByHId(id));
		irr.put("lines", irrDao.getIrrLinesByHId(id));
    	return ResultInfo.success(irr);
    }
    
    public ResultEntity deleteIrr(Long id) {
    	irrDao.deleteIrrByHId(id);
    	return ResultInfo.success();
    }
    
    public ResultEntity getIrrHeaders(Long userId, String interactCode) {
    	List<Map<String, Object>> irrHeaders=irrDao.getIrrHeaders(userId, interactCode);
    	return ResultInfo.success(irrHeaders);
    }
    
    public ResultEntity selectForPageIrrHeaders(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<Map<String, Object>> pageList = irrDao.selectForPageIrrHeaders(searchInfo);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }
    
    public ResultEntity insertIrr(Map<String, Object> headerParamMap,String linesStr,Long userId) {
    	irrDao.insertIrrHeader(headerParamMap);
    	//System.out.println("headerId:"+headerParamMap.get("id"));
    	if((Long)headerParamMap.get("id")>0){//由于行的新增必须要用到头id，所以，只好将行新增的获取参数部分到这里。
    		List<Map<String, Object>> linesParamMapList=new ArrayList<Map<String, Object>>();
    		JSONArray linesArray = (JSONArray) JSONArray.parse(linesStr);
    		for(int i=0;i<linesArray.size();i++){
    			JSONObject lineJson =linesArray.getJSONObject(i);
    			//System.out.println(lineJson.getString("columnPrompt")+":"+lineJson.getString("columnName"));
    			Map<String, Object> linesParamMap=new HashMap<String, Object>();
    			linesParamMap.put("lineSeq",i);
    			linesParamMap.put("headerId", headerParamMap.get("id"));
    			linesParamMap.put("columnPrompt", lineJson.getString("columnPrompt"));
    			linesParamMap.put("columnName", lineJson.getString("columnName"));
    			linesParamMap.put("createdBy",userId);
    			linesParamMap.put("lastUpdatedBy",userId);
    			linesParamMap.put("lastUpdateLogin",-1L);
    			linesParamMapList.add(linesParamMap);
    		}
    		Long insertLines=0L;
    		if(linesArray.size()>0){
    			insertLines=irrDao.insertIrrLines(linesParamMapList);
    		}
    		if(insertLines>0){
    			if(headerParamMap.get("defaultFlag").equals("Y")){
    				//System.out.println("当前的默认flag=Y，别的所有=Y的都要更新为N。因为默认打开只允许1个！");
    				Map<String, Object> udpParamMap=new HashMap<String, Object>();
    				udpParamMap.put("userId", userId);
    				udpParamMap.put("interactCode", headerParamMap.get("interactCode"));
    				udpParamMap.put("id", headerParamMap.get("id"));
    				Long upds=irrDao.updateIrrHeaderDefault(udpParamMap);
    				System.out.println("upds:"+upds);
    			}
    			return ResultInfo.success();
    		}else{
        		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    			return ResultInfo.error("生成交互式报表行失败！");
    		}
    	}else{
    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultInfo.error("生成交互式报表头失败！");
    	}
    }

}
