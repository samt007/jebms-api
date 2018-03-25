package com.jebms.comm.core;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Transient;

import tk.mybatis.mapper.common.Mapper;

import com.jebms.comm.security.model.AuthUser;
import com.jebms.comm.utils.StringUtil;
import com.jebms.comm.utils.TypeConverter;

/**
 * 基本实体类抽象封装，其他实体类的基类(父类)
 *
 * @author Sam.T
 * @version 1.0
 * @date 2017年9月4日
 */
public abstract class BaseEntity  implements Cloneable{
    /**
     * 创建人
     */
	@Column(updatable=false)
    private Long createdBy;

    /**
     * 创建时间
     */
	@Column(updatable=false)
    private Date creationDate;

    /**
     * 最后更新人
     */
    private Long lastUpdatedBy;

    /**
     * 最后更新时间
     */
    private Date lastUpdateDate;

    /**
     * 最后更新登录id
     */
    private Long lastUpdateLogin;
    
    /**
     * 对象(成员变量)的属性值的唯一识别码 (Universally Unique Identifier)
     */
    @Transient
    private String valueUUID;
    
    public Object mapRowCreator(Map<String, String> mapCol ,ResultSet rs, int rowNum) {
		Class<?> entityClass=this.getClass();
		Object entity;//Class<?> beanClass = c;
	    try {
		   entity = entityClass.newInstance();//这里相当于new Class();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	    Method[] methods = entityClass.getMethods();
	    for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith("set") == false || methodName.length() <= 3) {	// only setter method
		 continue;
		}
		Class<?>[] types = method.getParameterTypes();
		if (types.length != 1) {						// only one parameter
			continue;
		}
		String attrName = methodName.substring(3,4).toLowerCase()+methodName.substring(4);
		//System.out.println("methodName:"+methodName+",attrName:"+attrName);
		if (mapCol.containsKey(attrName)) {
			try {
				Object paraValue=rs.getObject(mapCol.get(attrName));
				//System.out.println("methodParaType:"+types[0]);
				Object value = paraValue != null ? TypeConverter.convert(types[0], paraValue.toString()) : null;
				method.invoke(entity, value);
				//System.out.println("set methodName:"+method.getName());
			} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
	       	return entity;
		}
		
		/**
		 * 2017.4.24 sam.t
		 * <br/>封装类似Form的FND_STANDARD.SET_WHO。
		 * 主要是为了方便设定对应对象的5WHO栏位。包括INSERT和UPDATE的动作
		 * @param <T>
		 */
	    public static final String INSERT="INSERT";
	    public static final String UPDATE="UPDATE";
		public void setWho(String dmlType,Long userId,Long loginId){
			if(!(dmlType.equalsIgnoreCase(INSERT)||dmlType.equalsIgnoreCase(UPDATE)))
				throw new IllegalArgumentException("The  parameters dmlType must be INSERT or UPDATE. Current dmlType:"+dmlType);
			Object entity=this;
			if(userId==null) userId=-1L;
			if(loginId==null) loginId=-1L;
			Class<? extends Object> entityClass=entity.getClass();
			try{
				if (dmlType.equalsIgnoreCase(INSERT)){
					Method setCreationDateMethod=entityClass.getMethod("setCreationDate", Date.class);
					Method setCreatedByMethod=entityClass.getMethod("setCreatedBy",Long.class);
					setCreationDateMethod.invoke(entity, new Date());
					setCreatedByMethod.invoke(entity, userId);
				}
				Method setLastUpdateDateMethod=entityClass.getMethod("setLastUpdateDate", Date.class);
				Method setLastUpdatedByMethod=entityClass.getMethod("setLastUpdatedBy",Long.class);
				Method setLastUpdateLoginMethod=entityClass.getMethod("setLastUpdateLogin",Long.class);
				setLastUpdateDateMethod.invoke(entity, new Date());
				setLastUpdatedByMethod.invoke(entity, userId);
				setLastUpdateLoginMethod.invoke(entity, loginId);
			}catch(Exception e){
				e.printStackTrace();//throw new RuntimeException(e.getMessage());
			}
		}

		public void setWhoUpdate(Long userId,Long loginId){
			setWho(UPDATE,userId,loginId);
		}

		public void setWhoInsert(Long userId,Long loginId){
			setWho(INSERT,userId,loginId);
		}

		public void setWhoUpdate(AuthUser user){
			setWho(UPDATE,user.getId(),user.getLoginId());
		}

		public void setWhoInsert(AuthUser user){
			setWho(INSERT,user.getId(),user.getLoginId());
		}

		/**
		 * 2017.6.12 sam.t
		 * <br/>封装2个Bean(Entity)对象的属性值对比，是否一致！
		 * 主要是为了方便更新前的属性对比。一致才允许更新数据！
		 * 添加一种情况：this是父类，o是子类，也允许进行属性对比！
		 * @param <T>
		 */
	    public boolean equalsVal(Object o) {  
			System.out.println("override!");
			System.out.println("this:"+this+",o:"+o);
	        if (this == o) return true;  
	        if(o==null) return false;
	        if(!this.getClass().isAssignableFrom(o.getClass())){
	        	if(getClass() != o.getClass()) return false;
	        }
	        //if (o == null || (getClass() != o.getClass()&&)) return false;  
	        //用反射机制获取对应的方法，逐一对比是否一致！
	        Method[] methods =this.getClass().getMethods();
	        for (Method method : methods) {
				String methodName = method.getName();
				//System.out.println("methodName:"+methodName);
				if (methodName.startsWith("get") == false || methodName.length() <= 3||methodName.equals("getClass")) {	// only setter method
					continue;
				}
				Class<?>[] types = method.getParameterTypes();
				if (types.length != 0) {						// No parameter
					continue;
				}
				Object thisVal;
				Object oVal;
				try {
					thisVal = method.invoke(this);
					oVal=method.invoke(o);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				System.out.println("methodName:"+methodName+",thisVal:"+thisVal+",oVal:"+oVal);
				
				if(!(thisVal!=null&&thisVal.equals(oVal)||(thisVal==null&&oVal==null))){
					System.out.println("false!!");
					return false;
				}
			}
	        return true;  
	    } 
	    

		/**
		 * 2017.9.12 sam.t
		 * <br/>封装Bean(Entity)对象的属性值UUID对比，是否一致！
		 * 主要是为了方便更新前的属性对比。一致才允许更新数据！
		 * @param <D>
		 */
	    public <D extends Mapper<T>,T extends BaseEntity> boolean equalValueUUID(D dao) {  
	    	if(this.getValueUUID()==null){
	    		return false;
	    	}
	    	@SuppressWarnings("unchecked")
			T lockRecord= (T) this.clone();
	    	lockRecord=  dao.selectByPrimaryKey(lockRecord);
	    	if(lockRecord!=null){
		    	lockRecord.setValueUUID();
		    	return this.getValueUUID().equals(lockRecord.getValueUUID());
	    	}else{
	    		return false;
	    	}
	    }
	    
	    public <T extends BaseEntity> boolean equalValueUUID(T obj) {  
	    	if(this.getValueUUID()==null||obj.getValueUUID()==null){
	    		return false;
	    	}
	    	return this.getValueUUID().equals(obj.getValueUUID());
	    }
	    
	    
		@Override
		public Object clone() {
			try{  
				return super.clone();  
			}catch(CloneNotSupportedException e) {  
				e.printStackTrace();  
				return null;
			}
		} 

		/*
		 * 5 WHO栏位
		 */
	    public Long getCreatedBy() {
	        return createdBy;
	    }

	    public void setCreatedBy(Long createdBy) {
	        this.createdBy = createdBy;
	    }

	    public Date getCreationDate() {
	        return creationDate;
	    }

	    public void setCreationDate(Date creationDate) {
	        this.creationDate = creationDate;
	    }

	    public Long getLastUpdatedBy() {
	        return lastUpdatedBy;
	    }

	    public void setLastUpdatedBy(Long lastUpdatedBy) {
	        this.lastUpdatedBy = lastUpdatedBy;
	    }

	    public Date getLastUpdateDate() {
	        return lastUpdateDate;
	    }

	    public void setLastUpdateDate(Date lastUpdateDate) {
	        this.lastUpdateDate = lastUpdateDate;
	    }

	    public Long getLastUpdateLogin() {
	        return lastUpdateLogin;
	    }

	    public void setLastUpdateLogin(Long lastUpdateLogin) {
	        this.lastUpdateLogin = lastUpdateLogin;
	    }

		public String getValueUUID() {
			return valueUUID;
		}

		public void setValueUUID() {
			//目前是自动根据5who栏位自动组合而成的唯一编码: 将5WHO字符串拼接成一个字符串进行sha1加密
			String whoString=
					String.valueOf(this.createdBy)
					+String.valueOf(this.creationDate.getTime())
					+String.valueOf(this.lastUpdatedBy)
					+String.valueOf(this.lastUpdateDate.getTime())+String.valueOf(this.lastUpdateLogin);
			if(whoString!=null){
				MessageDigest md = null;
				String tmpStr = null;
				try {
					md = MessageDigest.getInstance("SHA-1");
					// 将5WHO字符串拼接成一个字符串进行sha1加密
					byte[] digest = md.digest(whoString.toString().getBytes());
					tmpStr = StringUtil.byteToStr(digest);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					tmpStr=whoString;
				}
				//System.out.println("valueUUID tmpStr:"+tmpStr);
				this.valueUUID = tmpStr;
			}
		}
	    
		/*public static void main(String[] args){
			User u1=new User();
			u1.setId(1);
			u1.setUserNicename("wx214492");
			User u2=new User();
			u2.setId(1);
			u2.setUserNicename("wx214492");
			System.out.println("u1:"+u1+"-->u2:"+u2);
			
			System.out.println(u1.equals(u2));
		}*/
}
