/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.service;

import com.dematic.research.hazelcast.model.SimpleFact;

import java.util.Collection;

/**
 * @author glinkjoa
 */
public interface CachedFactService {

   void saveFact(SimpleFact fact);

   void saveFact(SimpleFact fact, long timeToLive);

   SimpleFact findFact(String id);

   Collection<SimpleFact> findAllFacts();

   void deleteFact(String id);

}
