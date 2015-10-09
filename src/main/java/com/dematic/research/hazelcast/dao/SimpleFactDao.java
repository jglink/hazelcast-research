/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.dao;

import com.dematic.research.hazelcast.model.SimpleFact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author glinkjoa
 */
@Repository
public interface SimpleFactDao extends CrudRepository<SimpleFact, String> {

}
