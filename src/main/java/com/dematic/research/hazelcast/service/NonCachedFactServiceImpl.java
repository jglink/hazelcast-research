/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.service;

import com.dematic.research.hazelcast.dao.SimpleFactDao;
import com.dematic.research.hazelcast.dao.SimpleSequenceModelDao;
import com.dematic.research.hazelcast.model.SimpleFact;
import com.dematic.research.hazelcast.model.SimpleSequenceIdModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Simple service to store the entities directly in the database.
 *
 * @author glinkjoa
 */
@Service
@Transactional
public class NonCachedFactServiceImpl implements NonCachedFactService {

   @Autowired
   private SimpleSequenceModelDao simpleSequenceModelDao;

   @Autowired
   private SimpleFactDao simpleFactDao;

   @Override
   public void saveFact(SimpleFact fact) {
      simpleFactDao.save(fact);
   }

   @Override
   public void saveSequenceIdModel(SimpleSequenceIdModel toSave) {
      simpleSequenceModelDao.save(toSave);
   }
}
