package com.hollysys.tn.common.mapper;

import java.util.List;
import java.util.Map;

/*
 * FilePath: null.java
 * Project: hydrogen
 * CreatedAt: 2020/5/27 20:24
 * CreatedBy: l_sy
 * Copyright: (c) 2019 HollySys
 * Task: #1
 * Write a description of the code here.
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

   public List<T> find(Map map);

   public int update(T entity);

   public int delete(T entity);

}
