/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.service;

import com.dematic.research.hazelcast.dao.CachedEntityDao;
import com.dematic.research.hazelcast.model.CachedEntity;
import com.dematic.research.hazelcast.model.SimpleFact;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Service implemenation which stores the given entities in a Hazelcast cache.
 * The used map has NO {@link com.hazelcast.core.MapStore} configuration; so the entities are only in RAM.
 * (The configured listener {@link com.dematic.research.hazelcast.listener.CacheEntityRemovedListener} is responsible
 * to store the entities which are removed from the cache.
 * The cache removal is triggerd by the Time-to-live parameter of the map.)
 *
 * @author glinkjoa
 */
@Service
public class OnlyCacheServiceImpl implements OnlyCacheService {

   @Autowired
   CachedEntityDao cachedEntityDao;

   @Override
   public void saveEntity(CachedEntity cachedEntity) {
      getCacheMap().put(cachedEntity.getId(), cachedEntity);
   }

   @Override
   public Collection<CachedEntity> findAllEntities() {
      return getCacheMap().values();
   }

   @Override
   public CachedEntity findEntity(String id) {
      return getCacheMap().get(id);
   }

   /**
    * Using Hazelcast Criteria API to search for cache-entries.
    * The search parameter here is not (or not only) the key!
    *
    * @param name
    * @return
    */
   @Override
   public Collection<CachedEntity> findEntitiesByName(String name) {
      EntryObject entryObject = new PredicateBuilder().getEntryObject();
      Predicate<String, CachedEntity> predicate = entryObject.get("fact").equal(name);
      Collection<CachedEntity> cacheResult = getCacheMap().values(predicate);

      if (CollectionUtils.isEmpty(cacheResult)) {
         // backup to DB query if no cache entry with given value is found.
         // (but what, if the cache is partial; so some entries with given value are in the cache but others only in the DB?)

         // in case of MFC Fact processing:
         //   - ConnectionMovementFacts (CMF) are loaded from the DB (it´s possible, that more than 1 CMF exists)
         //     -- the oldest CMF is relevant, so this on is most likely already evicted from the cache
         //     --> join of Query results from cache and DB needed!
         //         --> extra processing time!!!
         //             (already retrieved keys from cache could be used as parameter for DB query to exclude already retrieved CMFs)

         // Alternative: MapLoader???
         //   - yes and no; it´s a good way if the search is by key but not if the search is done by Criteria API
         //     -- in case of the ConnectionMovementFacts: maybe it´s a good idea to use a MultiMap and the transportId
         //        as key; so a simple MapLoader would resolve the problems... But what about Facts with multiple search queries?

         return cachedEntityDao.findByFact(name);
      }

      return cacheResult;
   }

   private IMap<String, CachedEntity> getCacheMap() {
      HazelcastInstance instance = Hazelcast.getHazelcastInstanceByName("spring-managed-hazelcast");
      return instance.getMap(CachedEntity.class.getName());
   }

}
