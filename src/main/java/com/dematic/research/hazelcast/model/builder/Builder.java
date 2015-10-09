/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.model.builder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author glinkjoa
 */
public abstract class Builder<T, B extends Builder> {

   public T build() {
      T instance = null;
      try {
         instance = getTypeParameterClass().newInstance();
      }
      catch (Exception e) {
         e.printStackTrace();
      }

      return instance;
   }

   @SuppressWarnings ("unchecked")
   public Class<T> getTypeParameterClass() {
      Type type = getClass().getGenericSuperclass();
      ParameterizedType paramType = (ParameterizedType) type;
      return (Class<T>) paramType.getActualTypeArguments()[0];
   }

}
