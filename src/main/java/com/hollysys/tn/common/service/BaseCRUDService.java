package com.hollysys.tn.common.service;
/*
* FilePath: null.java
* Project: hydrogen
* CreatedAt: 2020/5/27 20:25
* CreatedBy: l_sy
* Copyright: (c) 2019 HollySys
* Task: #1
* Write a description of the code here.
*/

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hollysys.tn.common.entity.AbstractEntity;
import com.hollysys.tn.common.exception.DaoException;
import com.hollysys.tn.common.log.MethodLogger;
import com.hollysys.tn.common.mapper.BaseMapper;
import com.hollysys.tn.common.util.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseCRUDService<M extends BaseMapper<T>, T extends AbstractEntity> extends ServiceImpl<M, T>
		implements BaseService<T> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Class<T> entityClass;

	protected String entityClassName;

	@Override
	@MethodLogger(methodDesc = "查询数据")
	public List<T> find(Map<String, Object> paramMap) {
		try {
			return  getBasesMapper().find(paramMap);
		} catch (Exception e) {
			logger.error("find error", e);
			throw new DaoException("DataAccessException", new String[] { paramMap.toString(), "find",
					ExceptionUtil.getExceptionStackTrace(e, ExceptionUtil.ExceptionLogLength) }, e);
		}
	}

	@Override
	@MethodLogger(methodDesc = "删除数据")
	public int delete(T entity) {
		try {
			return getBaseMapper().delete(entity);
		} catch (DataIntegrityViolationException e) {
			logger.error("DataIntegrityViolationException", e);
			throw new DaoException("DataIntegrityViolationException", new String[] { entity.getId().toString(),
					"delete", ExceptionUtil.getExceptionStackTrace(e, ExceptionUtil.ExceptionLogLength) }, e);
		} catch (Exception e) {
			logger.error("Other Exception", e);
			throw new DaoException("DeleteDataException", new String[] { entity.getId().toString(), "delete",
					ExceptionUtil.getExceptionStackTrace(e, ExceptionUtil.ExceptionLogLength) }, e);
		}
	}

	@Override
	@MethodLogger(methodDesc = "更新数据")
	public int update(T entity) {
		int count;
		try {
			entity.setUpdateUser("lushanyuan");
			entity.setUpdateDate(new Date());
			count = getBaseMapper().update(entity);
			if (count == 0) {
				throw new DaoException("DataNotFoundException", new String[] { entity.getId().toString(), "update" },
						null);
			}
			return count;
		} catch (DuplicateKeyException e) {
			logger.error("DuplicateKeyException", e);  // 唯一性
			throw new DaoException("DuplicateKeyException", new String[] { entity.getId().toString(), "update",
					ExceptionUtil.getExceptionStackTrace(e, ExceptionUtil.ExceptionLogLength) }, e);
		} catch (DataIntegrityViolationException e) {
			logger.error("DataIntegrityViolationException", e);
			throw new DaoException("DataIntegrityViolationException", new String[] { entity.getId().toString(),
					"update", ExceptionUtil.getExceptionStackTrace(e, ExceptionUtil.ExceptionLogLength) }, e);
		} catch (Exception e) {
			logger.error("Other Exception", e);
			throw new DaoException("UpdateDataException", new String[] { entity.getId().toString(), "update",
					ExceptionUtil.getExceptionStackTrace(e, ExceptionUtil.ExceptionLogLength) }, e);
		}
	}

	@Override
	@MethodLogger(methodDesc = "插入数据")
	public T insert(T entity) {
		entity.setCreateUser("lushanyuan");
		entity.setCreateDate(new Date());
		entity.setUpdateUser("lushanyuan");
		entity.setUpdateDate(new Date());
		entity.setVersion(1);
		try {
			getBasesMapper().insert(entity);
		}catch (Exception e) {
			logger.error("insertError", e);
		}
		return entity;
	}

	@Override
	@MethodLogger(methodDesc = "查询单条数据")
	public T findOne(Map<String, Object> map){
		return null;
	}

	/**
	 * 通过反射从泛型中获取类名称
	 */
	@SuppressWarnings("unchecked")
	public BaseCRUDService() {
		try {
			Object genericClz = getClass().getGenericSuperclass();
			if (genericClz instanceof ParameterizedType) {
				entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
						.getActualTypeArguments()[1];
				entityClassName = entityClass.getSimpleName();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从Bean工厂中获取Mapper (在启动项中已配置扫描mapper)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BaseMapper<T> getBasesMapper() {
		BaseMapper<T> baseMapper = null;
		if (this.entityClass != null) {
			baseMapper = (BaseMapper<T>) AppServiceHelper
					.findBean(StringUtils.uncapitalize(entityClassName) + "Mapper");
		}
		return baseMapper;
	}
}
