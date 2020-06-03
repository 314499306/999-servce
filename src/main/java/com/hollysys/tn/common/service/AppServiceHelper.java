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


import com.hollysys.tn.common.cache.CacheManager;
import com.hollysys.tn.common.exception.GlobalRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;


@Component
public class AppServiceHelper implements ApplicationContextAware {

	private static final  Logger logger = LoggerFactory.getLogger(AppServiceHelper.class);

	private static ApplicationContext applicationContext;
	
	private static I18nMessage i18nMessage;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AppServiceHelper.applicationContext = applicationContext;
	}

	public static Object findBean(String beanId) throws NoSuchBeanDefinitionException {
		Object service = applicationContext.getBean(beanId);
		return service;
	}

	public static String getMessage(String key, Object[] params, Locale locale) {
		
		if (locale==null) locale = new Locale("zh","CN");
		//从后台代码获取国际化信息
		String i18n = "";
		try {
			i18n = applicationContext.getMessage(key, params, locale);
		} catch (NoSuchMessageException e) {
			logger.warn("i18n definition for [" + key + "] not found in properties file.");
			if (getI18nMessage()!=null) {
				i18n = getI18nMessage().getMessage(key, params, locale);
			}
		}
		return i18n;
	}

	@SuppressWarnings(value="all")
	public static Object findBeanOfType(Class clz) {
		if(logger.isDebugEnabled()) {
			logger.debug("findBeanOfType="+ (clz==null?"Empty Class Name":clz.getName()));
		}
		if(clz==null) {
			return null;
		}
		Object service = CacheManager.getFromCache(clz.getName());
		if(service==null) {
			try {			
				Map<String, Object> serviceMap = applicationContext.getBeansOfType(clz);
				Iterator<String> beanNames=serviceMap.keySet().iterator();
				while(beanNames.hasNext()) {
					Object instance=serviceMap.get(beanNames.next());
					if(instance.getClass().equals(clz)) {
						service=instance;
					} else if(AopUtils.isAopProxy(instance)) {
						service=instance;
						break;
					}
				}
				CacheManager.putInCache(clz.getName(), service);
			} catch (NoSuchBeanDefinitionException ex) {
				throw new GlobalRuntimeException("no such bean for["+clz+"]", ex);
			} catch (BeansException ex) {
				throw new GlobalRuntimeException("bean exception for["+clz+"]", ex);
			}	
		}

		return service;
	}	
	
	public static I18nMessage getI18nMessage() {
		if (i18nMessage==null) {
			i18nMessage = (I18nMessage)AppServiceHelper.findBean("i18nDBMessage");
		}
		return i18nMessage;
	}
}
