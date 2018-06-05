/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mytest.common.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

/**
 * @author liqingyu
 * @since 2018/06/05
 */
public class CglibBeanCopierUtils {

    /** beanCopier缓存集合 */
    private static final Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

    /**
     * cglib的BeanCopier
     *
     * @param fromBean
     * @param toBean
     */
    public static void copy(Object fromBean, Object toBean) {
        if (fromBean == null || toBean == null) {
            return;
        }
        String key = generateKey(fromBean.getClass(), toBean.getClass());
        BeanCopier copier;
        if (!beanCopierMap.containsKey(key)) {
            copier = BeanCopier.create(fromBean.getClass(), toBean.getClass(), false);
            beanCopierMap.put(key, copier);
        } else {
            copier = beanCopierMap.get(key);
        }
        copier.copy(fromBean, toBean, null);
    }

    /**
     * 生成beanCopierMap的key
     //由两对象的包名+类目组合作为唯一键
     *
     * @param fromBean
     * @param toBean
     * @return
     */
    private static String generateKey(Class<?> fromBean, Class<?> toBean) {
        return fromBean.getName() + toBean.getName();
    }
}
