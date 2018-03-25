package com.jebms.fnd.dao;

import java.util.List;
import java.util.Map;

import com.jebms.comm.entity.SearchInfo;
import com.jebms.fnd.entity.FndRespVO;

/**
 * 查询的Dao
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年6月22日
 */
public interface RGDao {
	
    /**
     * RG记录组查询Dao
     * @param searchInfo 传入查询参数
     * @return
     */
    List<Map<String, Object>> selectForCustomer(SearchInfo searchInfo);
    
    List<FndRespVO> selectForUserResp(SearchInfo searchInfo);

    List<Map<String, Object>> selectForPageIcon(SearchInfo searchInfo);

    List<Map<String, Object>> selectForPageFunction(SearchInfo searchInfo);

    List<Map<String, Object>> selectForPageMenu(SearchInfo searchInfo);

    List<Map<String, Object>> selectForPageAppl(SearchInfo searchInfo);
    
    List<FndRespVO> selectForPageResp(SearchInfo searchInfo);

    List<Map<String, Object>> selectForPagePerson(SearchInfo searchInfo);

    List<Map<String, Object>> selectForPageDepartment(SearchInfo searchInfo);

}