package com.jebms.comm.entity;

import java.io.Serializable;

/**
 * 封装返回结果对象。
 * <br>code：0:成功  非0:失败( 或者：0:成功  1:警告   2:错误  ----注意：确定警告的时候要做什么动作)
 * <br>message：具体的处理结果信息
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年8月29日
 */
@SuppressWarnings("serial")
public class ResultEntity<T> implements Serializable {

	public static String CODE="code"; // 对应retcode
	public static String MESSAGE="message"; // 对应errbuf
	public static String DESCRIPTION="description";
	public static String PARAM1="param1";
	public static String PARAM2="param2";
	public static String PARAM3="param3";
	public static String PARAM4="param4";
	public static String PARAM5="param5";
	
    /**
     * 返回码，对应retcode
     */
    private String code;
    
    /**
     * 返回结果，对应errbuf
     */
    private String message;
    
    /**
     * 返回结果描述，按需使用
     */
    private String description;
    
    /**
     * 返回数据内容
     */
    private T obj;
    /**
     * 返回的5个扩展参数
     */
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;

    public ResultEntity() {
    }

    public ResultEntity(String code, String message) {
        this.code = code;
        this.message = message;
        this.description = "";
    }

    public ResultEntity(String code, String message, T obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
        this.description = "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public boolean isOk() {
        return this.code.equals(ResultEntityCodeEnum.SUCCESS.getCode());
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}
}
