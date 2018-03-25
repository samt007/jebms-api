package com.jebms.comm.core;

import java.lang.reflect.Method;

import com.jebms.comm.utils.Constant;

import tk.mybatis.mapper.common.Mapper;

/**
 * 多语言语言管理封装处理
 *
 * @author samt007@qq.com
 */

public class Language{

	/**
	 * 新增语言表处理。自动新增所有已经定义好的语言
	 * @param langDao
	 * @param entity
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <D extends Mapper<T>, T extends BaseEntity> void insertTl(D langDao,T entity ) throws Exception{
		Class<? extends BaseEntity> entityClass=entity.getClass();
		Method setLanguageMethod=entityClass.getMethod("setLanguage", String.class);
		Method getLanguageMethod=entityClass.getMethod("getLanguage");
		Method setSourceLangMethod=entityClass.getMethod("setSourceLang", String.class);
		String curLang=(String) getLanguageMethod.invoke(entity);
		setSourceLangMethod.invoke(entity, curLang);
		langDao.insert(entity);//首先新增一行当前语言
		//System.out.println(getLanguageMethod.invoke(entity)+"-->entity:"+entity);
		for(String lang:Constant.LANGUAGES.split(",")){
			if(!curLang.equals(lang)){
				T entityLang=(T) entity.clone();
				setLanguageMethod.invoke(entityLang,lang);
				langDao.insert(entityLang);
				//System.out.println(getLanguageMethod.invoke(entityLang)+"-->entityLang:"+entityLang);
			}
		}
	}
	
	/**
	 * 更新语言表处理
	 * 这里比较复杂的一个逻辑是：看来源语言，如果更新的语言和别的语言的来源语言一致，则相关的都会自动更新。
	 * @param langDao
	 * @param entity
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <D extends Mapper<T>, T extends BaseEntity> void updateTl(D langDao,T entity ) throws Exception{
		Class<? extends BaseEntity> entityClass=entity.getClass();
		Method setLanguageMethod=entityClass.getMethod("setLanguage", String.class);
		Method getLanguageMethod=entityClass.getMethod("getLanguage");
		Method setSourceLangMethod=entityClass.getMethod("setSourceLang", String.class);
		Method getSourceLangMethod=entityClass.getMethod("getSourceLang");
		String curLang=(String) getLanguageMethod.invoke(entity);
		if(curLang.equals(getSourceLangMethod.invoke(entity))){//如果当前语言=来源语言，要自动触发更新别的语言
			langDao.updateByPrimaryKey(entity);
			for(String lang:Constant.LANGUAGES.split(",")){
				if(!curLang.equals(lang)){
					T entityLang=(T) entity.clone();
					setLanguageMethod.invoke(entityLang,lang);
					T entityLangSel=langDao.selectByPrimaryKey(entityLang);
					System.out.println("entityLangSel:"+getSourceLangMethod.invoke(entityLangSel)+",curLang:"+curLang);
					if(entityLangSel!=null&&getSourceLangMethod.invoke(entityLangSel).equals(curLang)){
						setLanguageMethod.invoke(entityLang,lang);
						langDao.updateByPrimaryKey(entityLang);
					}
				}
			}
		}else{//如果当前语言<>来源语言，则需要更新自己，同时更新来源语言=当前语言
			setSourceLangMethod.invoke(entity, curLang);
			langDao.updateByPrimaryKey(entity);//更新当前语言
		}
	}

	/**
	 * 删除语言表处理
	 * 所有语言都同步删除
	 * @param langDao
	 * @param entity
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <D extends Mapper<T>, T extends BaseEntity> void deleteTl(D langDao,T entity ) throws Exception{
		Class<? extends BaseEntity> entityClass=entity.getClass();
		Method setLanguageMethod=entityClass.getMethod("setLanguage", String.class);
		Method getLanguageMethod=entityClass.getMethod("getLanguage");
		String curLang=(String) getLanguageMethod.invoke(entity);
		langDao.deleteByPrimaryKey(entity);
		for(String lang:Constant.LANGUAGES.split(",")){
			if(!curLang.equals(lang)){
				T entityLang=(T) entity.clone();
				setLanguageMethod.invoke(entityLang,lang);
				langDao.deleteByPrimaryKey(entityLang);
			}
		}
	}
}
