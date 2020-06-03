package com.hollysys.tn.common.mybatis;
/*
 * FilePath: null.java
 * Project: hydrogen
 * CreatedAt: 2020/5/27 20:24
 * CreatedBy: l_sy
 * Copyright: (c) 2019 HollySys
 * Task: #1
 * Write a description of the code here.
 */

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hollysys.tn.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		//this.setFieldValByName("version", 1, metaObject);
		if (metaObject.hasGetter("version")) {
			Object version = getFieldValByName("version", metaObject);
			if (StringUtil.isEmpty(version)) {
				setFieldValByName("version", 1, metaObject);
			}
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		setFieldValByName("updateUser", "陆善元", metaObject);
		setFieldValByName("updateDate", new Date(), metaObject);
	}
}
