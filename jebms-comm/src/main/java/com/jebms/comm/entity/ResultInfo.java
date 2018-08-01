package com.jebms.comm.entity;

import java.util.Map;

import com.jebms.comm.utils.TypeConverter;



/**
 * 提供返回结果对象。
 * <br>code：0:成功  非0:失败( 或者：0:成功  1:警告   2:错误  ----注意：确定警告的时候要做什么动作)
 * <br>message：具体的处理结果信息
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年8月29日
 */

@SuppressWarnings({"rawtypes","unchecked"})
public class ResultInfo {

	private static final ResultEntity RESPONSE_MESSAGE_SUCCESS = new ResultEntity(ResultEntityCodeEnum.SUCCESS.getCode(), "");

    public static ResultEntity success() {
        return RESPONSE_MESSAGE_SUCCESS;
    }

	public static <T> ResultEntity<T> success(T t) {
        return new ResultEntity(ResultEntityCodeEnum.SUCCESS.getCode(), "", t);
    }

    public static ResultEntity error() {
        return error("");
    }

    public static ResultEntity error(String message) {
        return error(ResultEntityCodeEnum.ERROR.getCode(), message);
    }

    public static ResultEntity error(String code, String message) {
        return error(code, message, null);
    }

    public static <T> ResultEntity<T> error(String code, String message, T t) {
    	//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return new ResultEntity(code, message, t);
    }
    
    public static ResultEntity fromParams(Map<String,Object> params) {
        if(params==null) return error("null params!");
        ResultEntity ret=error();
        ret.setCode(params.get(ResultEntity.CODE)==null?"2":TypeConverter.type2Str(params.get(ResultEntity.CODE)));
        ret.setMessage(params.get(ResultEntity.MESSAGE)==null?"":(String) params.get(ResultEntity.MESSAGE));
        ret.setDescription(params.get(ResultEntity.DESCRIPTION)==null?"":(String) params.get(ResultEntity.DESCRIPTION));
        ret.setParam1(TypeConverter.type2Str(params.get(ResultEntity.PARAM1)));
        ret.setParam2(TypeConverter.type2Str(params.get(ResultEntity.PARAM2)));
        ret.setParam3(TypeConverter.type2Str(params.get(ResultEntity.PARAM3)));
        ret.setParam4(TypeConverter.type2Str(params.get(ResultEntity.PARAM4)));
        ret.setParam5(TypeConverter.type2Str(params.get(ResultEntity.PARAM5)));
    	return ret;
    }

}
