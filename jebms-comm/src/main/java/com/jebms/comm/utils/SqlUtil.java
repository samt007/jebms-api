package com.jebms.comm.utils;

import java.util.Map;


/**
 * Sql语句公用处理。主要是为了封装一些常用的处理Sql语句的字符的代码。例如and语句这些的。
 * @author Sam.T 2016.8.9
 */
public class SqlUtil {
	
	/**
	 * 判断对应的值是否属于有效的。所谓有效，就是：非空就是有效。
	 * @param colValue
	 * @return 对应的值是否属于有效-->true：有效。
	 */
	public static boolean isVaildValue(Object colValue){
		if(colValue instanceof String){
			return !TypeConverter.isEmpty(((String) colValue).replace("%", "").replace("_", ""));
		}else{
			return !TypeConverter.isEmpty(colValue);
		}
	}
	
	/**
	 * 获取A=B或者A LIKE B的AND语句
	 * <br/>相当于在POST-QUERY里面的copy功能，生成colName=colValue或者colName Like colValue的语句
	 * <br/>顺便将对应的参数也一起给赋值了！一步到位！
	 * @param colName DB栏位名称
	 * @param colValue 对应的值
	 * @param colParamName 参数变量名称，就是#{colParamName} 或者:colParamName
	 * @param sqlStmtMode sql语句脚本模式：MyBatis或者Jdbc
	 * @param forceFlag 强制要该条件的标识，无论是否为空 (2017.1.5新增)
	 * @return sqlStmt用的and语句
	 */
	public static final String MY_BATIS="MyBatis";
	public static final String JDBC="Jdbc";
	public static String getAndStmt(String colName,Object colValue,String colParamName,String sqlStmtMode,Boolean forceFlag) throws Exception{
		//System.out.println("getAndStmt!!!colValue:"+colValue);
		if(!forceFlag&&!isVaildValue(colValue)){
			return "";
		}
		if (colName==null){
			throw new RuntimeException("所传入的参数colName不可以为空！");
		}
		if (TypeConverter.isEmpty(colParamName)) colParamName=colName;
		String ret=null;
		String keyWord=null;
		if(colValue instanceof String){//如果是字符
			if(((String) colValue).indexOf("%")!=-1||((String) colValue).indexOf("_")!=-1){
				keyWord="LIKE";
			}else{
				keyWord="=";
			}
		}else{
			keyWord="=";
		}
		if(sqlStmtMode.equals(MY_BATIS)){
			ret=" AND "+colName+" "+keyWord+" #{"+colParamName+"} ";
		}else if(sqlStmtMode.equals(JDBC)){
			ret=" AND "+colName+" "+keyWord+" :"+colParamName+" ";
		}else{
			throw new RuntimeException("所传入的参数sqlStmtMode有问题！应该是MyBatis或者Jdbc！");
		}
		//System.out.println("ret:"+ret);
		return ret;
	}
	
	public static String getAndStmtMyBatis(String colName,Object colValue,String colParamName,Boolean forceFlag) throws Exception{
		return SqlUtil.getAndStmt(colName, colValue, colParamName,MY_BATIS, forceFlag);
	}
	public static String getAndStmtMyBatis(String colName,Object colValue,String colParamName) throws Exception{
		return SqlUtil.getAndStmt(colName, colValue, colParamName,MY_BATIS, false);
	}
	public static final String CONDITION_MAP_NAME="conditionMap";
	public static String getAndStmtMyBatis(Map<String,Object> conditionMap,String colName,String colParamName,Boolean forceFlag) throws Exception{
		if(conditionMap==null) throw new RuntimeException("所传入的参数conditionMap不允许为空！");
		return SqlUtil.getAndStmt(colName, conditionMap.get(colParamName), CONDITION_MAP_NAME+"."+colParamName,MY_BATIS, forceFlag);
	}
	public static String getAndStmtMyBatis(Map<String,Object> conditionMap,String colName,String colParamName) throws Exception{
		return SqlUtil.getAndStmtMyBatis(conditionMap,colName, colParamName, false);
	}
	

	public static final String LOW_CODE="_L";
	public static final String HIGH_CODE="_H";
	/**
	 * 获取低水位和高水位的AND语句
	 * <br/>相当于在POST-QUERY里面的APP_FIND.QUERY_RANGE功能，
	 * 生成colName between colValueLow and colValueHigh 的语句
	 * <br/>注意：目前2个低水位和高水位的栏位名称，目前用：栏位+LOW_CODE和栏位+HIGH_CODE来作为占位符（此乃约定）
	 * <br/>逻辑：如果2个参数都为空，则还是返回between a and b。其实最好在外面判断。
	 * @param colName DB栏位名称
	 * @param colValueLow 低水位
	 * @param colValueHigh 高水位
	 * @param colParamNameLow/colParamNameHigh 参数变量名称，就是#{colParamName} 或者:colParamName
	 * @param sqlStmtMode sql语句脚本模式：MyBatis或者Jdbc
	 * @param forceFlag 强制要该条件的标识，无论是否为空 (2017.1.5新增)
	 * @return sqlStmt用的and语句
	 */
	public static String getAndStmt(
			String colName
			,Object colValueLow,Object colValueHigh
			,String colParamNameLow,String colParamNameHigh
			,String sqlStmtMode,Boolean forceFlag) throws Exception{
		if(!forceFlag&&!isVaildValue(colValueLow)&&!isVaildValue(colValueHigh)){
			return "";
		}
		if (colName==null){
			throw new RuntimeException("所传入的参数colName不可以为空！");
		}
		if (TypeConverter.isEmpty(colParamNameLow)) colParamNameLow=colName+LOW_CODE;
		if (TypeConverter.isEmpty(colParamNameHigh)) colParamNameHigh=colName+HIGH_CODE;
		String ret=null;
		if(isVaildValue(colValueLow)&&colValueLow.equals(colValueHigh)){
			if(sqlStmtMode.equals(MY_BATIS)){
				ret=" AND "+colName+" ="+" #{"+colParamNameLow+"} ";
			}else if(sqlStmtMode.equals(JDBC)){
				ret=" AND "+colName+" ="+" :"+colParamNameLow+" ";
			}
		} else if(!isVaildValue(colValueLow)&&isVaildValue(colValueHigh)){
			if(sqlStmtMode.equals(MY_BATIS)){
				ret=" AND "+colName+" <="+" #{"+colParamNameHigh+"} ";
			}else if(sqlStmtMode.equals(JDBC)){
				ret=" AND "+colName+" <="+" :"+colParamNameHigh+" ";
			}
		} else if(isVaildValue(colValueLow)&&!isVaildValue(colValueHigh)){
			if(sqlStmtMode.equals(MY_BATIS)){
				ret=" AND "+colName+" >="+" #{"+colParamNameLow+"} ";
			}else if(sqlStmtMode.equals(JDBC)){
				ret=" AND "+colName+" >="+" :"+colParamNameLow+" ";
			}
		}else{
			if(sqlStmtMode.equals(MY_BATIS)){
				ret=" AND "+colName+" between"+" #{"+colParamNameLow+"} "+" and #{"+colParamNameHigh+"} ";
			}else if(sqlStmtMode.equals(JDBC)){
				ret=" AND "+colName+" between"+" :"+colParamNameLow+" "+" and :"+colParamNameHigh+" ";
			}
		}
		return ret;
	}
	
	public static String getAndStmtMyBatis(
			String colName
			,Object colValueLow,Object colValueHigh
			,String colParamNameLow,String colParamNameHigh
			,Boolean forceFlag) throws Exception{
		return getAndStmt(colName,colValueLow,colValueHigh,colParamNameLow,colParamNameHigh,MY_BATIS,forceFlag);
	}
	public static String getAndStmtMyBatis(
			String colName
			,Object colValueLow,Object colValueHigh
			,String colParamNameLow,String colParamNameHigh) throws Exception{
		return getAndStmt(colName,colValueLow,colValueHigh,colParamNameLow,colParamNameHigh,MY_BATIS,false);
	}
	public static String getAndStmtMyBatis(
			Map<String,Object> conditionMap,String colName
			,String colParamNameLow,String colParamNameHigh
			,Boolean forceFlag) throws Exception{
		if(conditionMap==null) throw new RuntimeException("所传入的参数conditionMap不允许为空！");
		return getAndStmt(colName,conditionMap.get(colParamNameLow),conditionMap.get(colParamNameHigh)
				,CONDITION_MAP_NAME+"."+colParamNameLow,CONDITION_MAP_NAME+"."+colParamNameHigh,MY_BATIS,forceFlag);
	}
	public static String getAndStmtMyBatis(
			Map<String,Object> conditionMap,String colName
			,String colParamNameLow,String colParamNameHigh) throws Exception{
		return getAndStmtMyBatis(conditionMap,colName, colParamNameLow, colParamNameHigh,false);
	}
	

	/**
	 * 获取下横线组合的order by语句。
	 * 例如：creationDate, lastUpdatedBy ASC ==> creation_date,last_updated_by ASC
	 */
	public static String getUnderScoreOrderBy(String orderBy){
		if(TypeConverter.isEmpty(orderBy)){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(String order:orderBy.split(",")){
			for(String tmp:order.split(" ")){
				if(tmp.toUpperCase().equals("ASC")||tmp.toUpperCase().equals("DESC")){
					sb.append(" "+tmp);
				}else{
					sb.append(StringHelper.toUnderScoreCase(tmp));
				}
			}
			sb.append(",");
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
	
	public static void main(String[] args){
		System.out.println(SqlUtil.getUnderScoreOrderBy("creationDate, lastUpdatedBy ASC"));
	}
	
	/*
	public static void main(String[] args){
		String userId="";
		String glassIndustry="%%%";
		System.out.println(glassIndustry.replace("%", "").replace("_", ""));
		System.out.println(SqlUtil.isVaildValue(glassIndustry));
		StringBuffer sqlBuf=new StringBuffer();
		
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("userId", 123);
		conditionMap.put("glassIndustry", "");
		conditionMap.put("organizationCodeLow", "456");
		conditionMap.put("organizationCodeHigh", "456");
		conditionMap.put("organizationName", "%555%");
		sqlBuf.append("SELECT ORGANIZATION_ID,ORGANIZATION_CODE,ORGANIZATION_NAME FROM XYG_ALB2B_ONHAND_PERM_V");
		sqlBuf.append(" WHERE  1= 1");
		try {
			sqlBuf.append(SqlUtil.getAndStmtMyBatis(conditionMap,"USER_ID","userId",true));
			sqlBuf.append(SqlUtil.getAndStmtMyBatis(conditionMap,"GLASS_INDUSTRY","glassIndustry"));
			//sqlBuf.append(SqlUtil.getAndStmtMyBatis("ORGANIZATION_CODE","123","organizationCode"));
			sqlBuf.append(SqlUtil.getAndStmtMyBatis(conditionMap,"ORGANIZATION_CODE","organizationCodeLow","organizationCodeHigh"));
			sqlBuf.append(SqlUtil.getAndStmtMyBatis(conditionMap,"ORGANIZATION_NAME","organizationName"));
			sqlBuf.append(" ORDER BY ORGANIZATION_ID");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sqlBuf:"+sqlBuf.toString());
	}*/
}

 
