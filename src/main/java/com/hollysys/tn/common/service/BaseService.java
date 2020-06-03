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

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface BaseService<T> extends IService<T>{

    public List<T> find(Map<String, Object> params);

    public int delete(T entity);

    public int update(T entity);

    public T insert(T entity) ;

    public T findOne( Map<String, Object> params );

}
