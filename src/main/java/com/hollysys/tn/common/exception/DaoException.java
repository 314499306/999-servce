package com.hollysys.tn.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 数据层运行异常定义
 */
public class DaoException extends BaseRuntimeException {
	private static final long serialVersionUID = -5803265594743554136L;

	private static final  Logger logger = LoggerFactory.getLogger(DaoException.class);
	public DaoException(String code,String[] params,Exception e) {
		super(code,e);
		this.setCode(code);
		this.setParams(params);
	}
	
}
