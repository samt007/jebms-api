package com.jebms.fnd.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.core.Language;
import com.jebms.comm.core.Transaction;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.comm.utils.SqlUtil;
import com.jebms.fnd.dao.FndLookupTypeDao;
import com.jebms.fnd.dao.FndLookupTypeTlDao;
import com.jebms.fnd.dao.FndLookupValueDao;
import com.jebms.fnd.entity.FndLookupTypeTl;
import com.jebms.fnd.entity.FndLookupTypeVO;
import com.jebms.fnd.entity.FndLookupValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Lookup字典表的Service封装。也是一个的Master-Detail模型Service
 *
 * @author samt007@qq.com
 */

@Service
@SuppressWarnings("rawtypes")
public class FndLookupService {
    
    @Autowired
    private FndLookupTypeDao lookupTypeDao;
    
    @Autowired
    private FndLookupTypeTlDao lookupTypeTlDao;
    
    @Autowired
    private FndLookupValueDao lookupValueDao;
    
    //如果存在多语言的处理，则直接创建该成员变量即可
    private Language lang=new Language();

    //增删改Lookup头
	public ResultEntity selectForPageLookupType(SearchInfo searchInfo) throws Exception {
    	StringBuffer sqlConditionBuf=new StringBuffer();
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"flt.lookup_type","lookupType"));
    	//sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"bk.start_date","startDateF","startDateT"));
    	searchInfo.setSqlCondition(sqlConditionBuf.toString());
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndLookupTypeVO> pageList = lookupTypeDao.selectForPage(searchInfo);
        PageInfo<FndLookupTypeVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public FndLookupTypeVO selectLookupTypeVOByPK(Long applId,String lookupType,String lang) {
    	FndLookupTypeVO ret=lookupTypeDao.selectVOByPK(applId,lookupType,lang);
    	ret.setValueUUID();
    	return ret;
    }
    
    @Transactional(readOnly = false)
    public ResultEntity insertLookupType(FndLookupTypeVO record,AuthUser user) {
    	record.setWhoInsert(user);
    	if(lookupTypeDao.insert(record)==1){
    		//新增语言表
    		FndLookupTypeTl tl=new FndLookupTypeTl(record.getApplId(),record.getLookupType(),user.getLanguage());
    		tl.setMeaning(record.getMeaning());
    		tl.setDescription(record.getDescription());
    		tl.setWhoInsert(user);
    		try {
				lang.insertTl(lookupTypeTlDao, tl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("新增语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectLookupTypeVOByPK(record.getApplId(),record.getLookupType(), user.getLanguage()));
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateLookupType(FndLookupTypeVO record,AuthUser user) {
    	if(!record.equalValueUUID(lookupTypeDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	if(lookupTypeDao.updateByPrimaryKey(record)==1){
    		//同步更新语言表
    		FndLookupTypeTl tl=new FndLookupTypeTl(record.getApplId(),record.getLookupType(),user.getLanguage());
    		tl=lookupTypeTlDao.selectByPrimaryKey(tl);//取数据库的记录
    		tl.setMeaning(record.getMeaning());
    		tl.setDescription(record.getDescription());
    		tl.setWhoUpdate(user);
    		try {
				lang.updateTl(lookupTypeTlDao, tl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("更新语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success(this.selectLookupTypeVOByPK(record.getApplId(),record.getLookupType(), user.getLanguage()));
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteLookupType(FndLookupTypeVO record,AuthUser user) {
    	if(lookupTypeDao.deleteByPrimaryKey(record)==1){
    		//同步删除语言表
    		FndLookupTypeTl tl=new FndLookupTypeTl(record.getApplId(),record.getLookupType(),user.getLanguage());
    		try {
				lang.deleteTl(lookupTypeTlDao, tl);
			} catch (Exception e) {
				Transaction.setRollbackOnly();
				return ResultInfo.error("删除语言表数据失败！信息:"+e.getMessage());
			}
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }
    
    //增删改Lookup行
	public ResultEntity selectForPageLookupValue(SearchInfo searchInfo) throws Exception {
    	StringBuffer sqlConditionBuf=new StringBuffer();
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"flv.appl_id","applId"));
    	sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"flv.lookup_type","lookupType"));
    	//sqlConditionBuf.append(SqlUtil.getAndStmtMyBatis(searchInfo.getConditionMap(),"bk.start_date","startDateF","startDateT"));
    	searchInfo.setSqlCondition(sqlConditionBuf.toString());
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndLookupValue> pageList = lookupValueDao.selectForPage(searchInfo);
        PageInfo<FndLookupValue> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

    public FndLookupValue selectLookupValueByPK(Long applId,String lookupType,String lookupCode,String lang) {
    	FndLookupValue ret=lookupValueDao.selectVOByPK(applId,lookupType,lookupCode,lang);
    	ret.setValueUUID();
    	return ret;
    }
    
    // 这个处理比较特殊，因为语言表和主表合在一起了！
    @Transactional(readOnly = false)
    public ResultEntity insertLookupValue(FndLookupValue record,AuthUser user) {
    	record.setWhoInsert(user);
    	record.setLanguage(user.getLanguage());
		try {
			lang.insertTl(lookupValueDao, record);
		} catch (Exception e) {
			Transaction.setRollbackOnly();
			return ResultInfo.error("新增语言表数据失败！信息:"+e.getMessage());
		}
		return ResultInfo.success(this.selectLookupValueByPK(record.getApplId(),record.getLookupType(),record.getLookupCode(),user.getLanguage()));
    }
    
    @Transactional(readOnly = false)
    public ResultEntity updateLookupValue(FndLookupValue record,AuthUser user) {
    	if(!record.equalValueUUID(lookupValueDao)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新！");
    	}
    	record.setWhoUpdate(user);
    	record.setLanguage(user.getLanguage());
		try {
			lang.updateTl(lookupValueDao, record);
		} catch (Exception e) {
			Transaction.setRollbackOnly();
			return ResultInfo.error("新增语言表数据失败！信息:"+e.getMessage());
		}
		return ResultInfo.success(this.selectLookupValueByPK(record.getApplId(),record.getLookupType(),record.getLookupCode(),user.getLanguage()));
    }
    
    @Transactional(readOnly = false)
    public ResultEntity deleteLookupValue(FndLookupValue record,AuthUser user) {
    	record.setLanguage(user.getLanguage());
		try {
			lang.deleteTl(lookupValueDao, record);
		} catch (Exception e) {
			Transaction.setRollbackOnly();
			e.printStackTrace();
			return ResultInfo.error("处理语言表数据失败！信息:"+e.getMessage());
		}
		return ResultInfo.success();
    }
}
