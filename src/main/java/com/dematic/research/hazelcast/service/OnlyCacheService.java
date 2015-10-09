/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.service;

import com.dematic.research.hazelcast.model.CachedEntity;

import java.util.Collection;

/**
 * @author glinkjoa
 */
public interface OnlyCacheService {

   void saveEntity(CachedEntity cachedEntity);

   Collection<CachedEntity> findAllEntities();

   CachedEntity findEntity(String id);

   Collection<CachedEntity> findEntitiesByName(String name);

}
