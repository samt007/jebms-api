package com.jebms.comm.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.security.model.AuthUser;
import com.jebms.comm.security.util.UserUtil;

import tk.mybatis.mapper.common.Mapper;

/**
 * 对单表做增删改查的封装好的的业务方法
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月22日
 */
@Service
@SuppressWarnings("rawtypes")
public abstract class BaseService<D extends Mapper<T>,T extends BaseEntity> {

    @Autowired
    protected D mapper;
    
    protected AuthUser authUser;

    public D getMapper() {
        return mapper;
    }
    
    public AuthUser getAuthUser() {
        this.authUser=UserUtil.getCurrentUser(); // 2017.11.8修改
    	return this.authUser;
    }
    
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T selectByPrimaryKey(T record) {
		record=(T) mapper.selectByPrimaryKey(record);
		((BaseEntity) record).setValueUUID();
        return record;
    }
	
	public List<T> selectAll() {
        return (List<T>) mapper.selectAll();
    }
	
    public ResultEntity insert(T record) {
    	//record.setWhoInsert(authUser); 这个动作必须在新增之前要做。
    	if(mapper.insert(record)==1){
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("新增数据失败！");
    	}
    }

	//Param1记录新增行的记录数
    public ResultEntity insertAll(List<T> recordList) {
    	int insertLines=0;
    	for(T record:recordList){
    		//record.setWhoInsert(authUser);
    		if(mapper.insert(record)==1){
    			insertLines++;
    		}else{
    			return ResultInfo.error("新增数据失败！");
    		}
    	}
    	ResultEntity ret=ResultInfo.success();
    	ret.setParam1(String.valueOf(insertLines));
    	return ret;
    }
    
	public ResultEntity update(T record) {
    	//Update之前必须要先检查数据是否和数据库一致。逻辑是：
    	//输入的参数record是没更新之前的数据，所以，理论上应该要和数据库的一样才对！
    	//所以，这里要先根据ID来获取数据库的记录，然后再所有属性和record做对比。一样，则Lock成功。不一样则Lock失败。
    	if(!record.equalValueUUID(mapper)){
    		return ResultInfo.error("数据已经被修改！请重新查询再更新数据！");
    	}
    	//record.setWhoUpdate(authUser);这个动作必须在更新之前要做。
		if(mapper.updateByPrimaryKey(record)==1){
			return ResultInfo.success(this.selectByPrimaryKey(record));
		}else{
			return ResultInfo.error("未更新数据！请检查条件！");
		}
    }

    public ResultEntity delete(T record) {
    	if(mapper.deleteByPrimaryKey(record)==1){
    		return ResultInfo.success();
    	}else{
    		return ResultInfo.error("未删除数据！请检查条件！");
    	}
    }
    
    /**
     * 单表分页查询
     * @param searchInfo 传入当前分页数 和 搜索信息。目前暂时不支持查询条件的输入。
     * @return
     */
    public ResultEntity selectForPage(SearchInfo searchInfo) throws Exception{
        if(searchInfo==null){
        	return ResultInfo.error("searchInfo不允许为空！");
        }
        if(searchInfo.getConditionMap()!=null&&searchInfo.getConditionMap().size()>0){
        	return ResultInfo.error("单表查询目前不支持带条件的查询！");
        }
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<T> recordList = mapper.select(null);//countryVODao.findCountryVO(searchInfo);
        System.out.println("recordList Size is:"+recordList.size());
        PageInfo<T> recordPageInfo = new PageInfo<>(recordList);
        return ResultInfo.success(recordPageInfo);
    }
    /*
    public List<T> selectPage(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        //Spring4支持泛型注入
        return mapper.select(null);
    }*/
}
