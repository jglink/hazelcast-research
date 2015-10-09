/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.dao;

import com.dematic.research.hazelcast.model.CachedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author glinkjoa
 */
@Repository
public interface CachedEntityDao extends CrudRepository<CachedEntity, String> {

   Collection<CachedEntity> findByFact(String fact);

}
