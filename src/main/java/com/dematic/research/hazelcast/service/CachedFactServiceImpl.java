/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.service;

import com.dematic.research.hazelcast.model.SimpleFact;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Services stores the given Facts in a Hazelcast Cache.
 * The entities are persisted with a {@link com.hazelcast.core.MapStore} implementation.
 *
 * @author glinkjoa
 */
@Service
@Transactional
public class CachedFactServiceImpl implements CachedFactService {

   @Override
   public void saveFact(SimpleFact fact) {
      getFactsMap().put(fact.getId(), fact);
   }

   @Override
   public void saveFact(SimpleFact fact, long timeToLive) {
      getFactsMap().put(fact.getId(), fact, timeToLive, TimeUnit.SECONDS);
   }

   @Override
   public SimpleFact findFact(String id) {
      return getFactsMap().get(id);
   }

   @Override
   public Collection<SimpleFact> findAllFacts() {
      return getFactsMap().values();
   }

   @Override
   public void deleteFact(String id) {
      getFactsMap().remove(id);
   }

   private IMap<String, SimpleFact> getFactsMap() {
      HazelcastInstance instance = Hazelcast.getHazelcastInstanceByName("spring-managed-hazelcast");
      return instance.getMap(SimpleFact.class.getName());
   }

}
