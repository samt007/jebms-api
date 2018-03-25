package com.jebms.fnd.dao;


/**
 * 国家查询的Dao
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月22日
 */
import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.Country;
import com.jebms.fnd.entity.CountryVO;

public interface CountryDao extends Mapper<Country> {
	
    /**
     * 查询所有国家
     * 这个可以不用封装一个Entity也可以接受查询返回的结果。
     * @param searchInfo 传入查询参数
     * @return
     */
    List<Map<String, Object>> selectForPage(SearchInfo searchInfo);
	
    CountryVO selectVOByPK(Long id);

}