package com.hollysys.tn.common.service;
/*
 * FilePath: null.java
 * Project: hydrogen
 * CreatedAt: 2020/5/27 20:24
 * CreatedBy: l_sy
 * Copyright: (c) 2019 HollySys
 * Task: #1
 * Write a description of the code here.
 */

import java.util.Locale;

public interface I18nMessage {
	public String getMessage(String key, Object[] params, Locale locale);
	public String getMessageDirect(String key, Object[] params, Locale locale);
}
