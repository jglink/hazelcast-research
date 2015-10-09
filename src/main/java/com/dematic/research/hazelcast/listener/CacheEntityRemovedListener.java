/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.listener;

import com.dematic.research.hazelcast.dao.CachedEntityDao;
import com.dematic.research.hazelcast.model.CachedEntity;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.MapEvent;
import com.hazelcast.map.listener.EntryRemovedListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * CacheListener implementation.
 * Receives events if an entity is added/removed/evicted to/from the cache.
 * If it´s configured als 'local', only the listener instance is notified which belongs to the 'owning' Hazelcast
 * node of the entity; otherwise all listener instances are notified.
 *
 * It´s a good idea to execute the listener code in separate threads, so the performance of the map is not harmed.
 *
 * @author glinkjoa
 */
public class CacheEntityRemovedListener implements EntryListener<String, CachedEntity> {

   private static final Logger LOGGER = Logger.getLogger(CacheEntityRemovedListener.class.getName());

   private Executor executor = Executors.newFixedThreadPool(5);

   @Autowired
   CachedEntityDao cachedEntityDao;

   @Override
   public void entryEvicted(final EntryEvent<String, CachedEntity> event) {
      executor.execute(new Runnable() {
         @Override
         public void run() {
            LOGGER.info(
                  String.format(">>>>>> cache entry evicted - entity: %s / %s", event.getKey(), event.getOldValue()));
            cachedEntityDao.save(event.getOldValue());
         }
      });
   }

   @Override
   public void entryRemoved(final EntryEvent<String, CachedEntity> event) {

   }

   @Override
   public void entryAdded(EntryEvent<String, CachedEntity> event) {

   }

   @Override
   public void entryUpdated(EntryEvent<String, CachedEntity> event) {

   }

   @Override
   public void mapCleared(MapEvent event) {

   }

   @Override
   public void mapEvicted(MapEvent event) {

   }
}
