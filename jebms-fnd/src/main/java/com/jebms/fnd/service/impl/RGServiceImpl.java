package com.jebms.fnd.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jebms.comm.entity.ResultEntity;
import com.jebms.comm.entity.ResultInfo;
import com.jebms.comm.entity.SearchInfo;
import com.jebms.comm.springjdbc.DevJdbcDaoSupport;
import com.jebms.fnd.dao.RGDao;
import com.jebms.fnd.entity.FndRespVO;
import com.jebms.fnd.service.RGService;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 记录组的业务方法
 * 提供所有的LOV和List所需要的查询！
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月25日
 */

@Service
@SuppressWarnings("rawtypes")
public class RGServiceImpl extends DevJdbcDaoSupport implements RGService {

    @Autowired
    private RGDao rgDao;
    
    /*
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }*/
    
	@Autowired
	RGServiceImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
    
	public ResultEntity getPageCustomer(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<Map<String, Object>> list = rgDao.selectForCustomer(searchInfo);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return ResultInfo.success(pageInfo);
    }
    
    public List<Map<String,Object>> getLookups(String lookupType,String lang) throws Exception{
		String sql = "SELECT MEANING display,LOOKUP_CODE value"
				+ " FROM fnd_lookup_values  "
				+ " WHERE LOOKUP_TYPE = :1 "
				+ "   AND LANGUAGE = :2 "
				+ " ORDER BY 1 ";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", lookupType);
		paramMap.put("2", lang);
    	return this.getDevJdbcTemplate().queryForList(sql,paramMap);
    }
    
	public ResultEntity getPageUserResp(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndRespVO> pageList = rgDao.selectForUserResp(searchInfo);
        PageInfo<FndRespVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }
    
	public ResultEntity selectForPageIcon(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<Map<String, Object>> pageList = rgDao.selectForPageIcon(searchInfo);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }
    
	public ResultEntity selectForPageFunction(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<Map<String, Object>> pageList = rgDao.selectForPageFunction(searchInfo);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }
    
	public ResultEntity selectForPageMenu(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<Map<String, Object>> pageList = rgDao.selectForPageMenu(searchInfo);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }
    
	public ResultEntity selectForPageAppl(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<Map<String, Object>> pageList = rgDao.selectForPageAppl(searchInfo);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

	public ResultEntity selectForPageResp(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<FndRespVO> pageList = rgDao.selectForPageResp(searchInfo);
        PageInfo<FndRespVO> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }

	public ResultEntity selectForPagePerson(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<Map<String, Object>> pageList = rgDao.selectForPagePerson(searchInfo);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }
    
    public ResultEntity getPeopleById(Long id) throws Exception{
		String sql = "SELECT id id,emp_number empNumber,full_name fullName FROM per_people  WHERE id = :1 ";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", id);
    	return ResultInfo.success(this.getDevJdbcTemplate().queryForList(sql,paramMap));
    }
    
    public ResultEntity getPeopleByNum(String empNumber) throws Exception{
		String sql = "SELECT id id,emp_number empNumber,full_name fullName FROM per_people  WHERE emp_number = :1 ";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", empNumber);
    	return ResultInfo.success(this.getDevJdbcTemplate().queryForList(sql,paramMap));
    }
    
    public ResultEntity getPeopleByFname(String fullName) throws Exception{
		String sql = "SELECT id id,emp_number empNumber,full_name fullName FROM per_people  WHERE full_name = :1 ";
		Map<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("1", fullName);
    	return ResultInfo.success(this.getDevJdbcTemplate().queryForList(sql,paramMap));
    }

	public ResultEntity selectForPageDepartment(SearchInfo searchInfo) throws Exception {
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize() ,searchInfo.isCount());
        List<Map<String, Object>> pageList = rgDao.selectForPageDepartment(searchInfo);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pageList);
        return ResultInfo.success(pageInfo);
    }
}
