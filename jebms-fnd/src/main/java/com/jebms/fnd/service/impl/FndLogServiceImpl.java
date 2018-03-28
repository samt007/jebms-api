package com.jebms.fnd.service.impl;


import com.jebms.comm.core.BaseService;
import com.jebms.fnd.dao.FndLogDao;
import com.jebms.fnd.entity.FndLog;
import com.jebms.fnd.service.FndLogService;

import org.springframework.stereotype.Service;

/**
 * 日志管理Service
 *
 * @author samt007@qq.com
 */

@Service
public class FndLogServiceImpl extends BaseService<FndLogDao,FndLog> implements FndLogService {

}
