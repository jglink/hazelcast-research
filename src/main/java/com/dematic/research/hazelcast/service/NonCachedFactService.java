/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.service;

import com.dematic.research.hazelcast.model.SimpleFact;
import com.dematic.research.hazelcast.model.SimpleSequenceIdModel;

/**
 * @author glinkjoa
 */
public interface NonCachedFactService {

   void saveFact(SimpleFact fact);

   void saveSequenceIdModel(SimpleSequenceIdModel toSave);

}
