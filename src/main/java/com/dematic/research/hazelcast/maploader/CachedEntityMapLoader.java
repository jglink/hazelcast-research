/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.maploader;

import com.dematic.research.hazelcast.dao.CachedEntityDao;
import com.dematic.research.hazelcast.model.CachedEntity;
import com.hazelcast.core.MapLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author glinkjoa
 */
public class CachedEntityMapLoader implements MapLoader<String, CachedEntity> {

   private static final Logger LOGGER = Logger.getLogger(CachedEntityMapLoader.class.getName());

   @Autowired
   CachedEntityDao cachedEntityDao;

   @Override
   public CachedEntity load(String key) {
      return cachedEntityDao.findOne(key);
   }

   @Override
   public Map<String, CachedEntity> loadAll(Collection<String> keys) {
      Iterable<CachedEntity> results = cachedEntityDao.findAll(keys);
      if (results != null) {
         Map<String, CachedEntity> map = new HashMap<>();
         for (CachedEntity cachedEntity : results) {
            map.put(cachedEntity.getId(), cachedEntity);
         }
         return map;
      }
      return null;
   }

   @Override
   public Iterable<String> loadAllKeys() {
      LOGGER.warning("CachedEntityMapLoader#loadAllKeys not implemented!!!");

      // intelligent query should be used to only load the keys which are likely to be used / searched for in the cache

      // Example for MFC Fact processing:
      //  - ConnectionMovementFact#endTime is null

      return null;
   }

}
