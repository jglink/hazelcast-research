/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.mapstore;

import com.dematic.research.hazelcast.dao.SimpleFactDao;
import com.dematic.research.hazelcast.model.SimpleFact;
import com.hazelcast.core.MapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * Hazelcast mapstore implementation with Spring injection (so the Spring Data DAOs could be used to persist the
 * cached objects)
 *
 * @author glinkjoa
 */
@Component
public class SimpleFactMapStore implements MapStore<String, SimpleFact> {

   @Autowired
   private SimpleFactDao simpleFactDao;

   @Override
   public void store(String key, SimpleFact value) {
      System.out.println("store "+key+"/"+value);
      try {
         simpleFactDao.save(value);
      }
      catch (Exception e) {
         System.out.println(">>>>>>>>>>>>>>>>>> Exception caught; fire-and-forget...");
      }
   }

   @Override
   public void storeAll(Map<String, SimpleFact> map) {
      System.out.println("store all "+map);
      if (map != null && map.values() != null) {
         for (SimpleFact toSave : map.values()) {
            simpleFactDao.save(toSave);
         }
      }
   }

   @Override
   public void delete(String key) {
      System.out.println("delete "+key);
      simpleFactDao.delete(key);
   }

   @Override
   public void deleteAll(Collection<String> keys) {
      System.out.println("delete all "+keys);
   }

   @Override
   public SimpleFact load(String key) {
      System.out.println("load "+key);
      return simpleFactDao.findOne(key);
   }

   @Override
   public Map<String, SimpleFact> loadAll(Collection<String> keys) {
      System.out.println("loadAll "+keys);
      return null;
   }

   @Override
   public Iterable<String> loadAllKeys() {
      System.out.println("loadAllKeys");
      return null;
   }

}
