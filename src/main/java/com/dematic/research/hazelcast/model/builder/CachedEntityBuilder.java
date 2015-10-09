/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.model.builder;

import java.util.Date;
import java.util.UUID;

import com.dematic.research.hazelcast.model.CachedEntity;
import com.dematic.research.hazelcast.model.SimpleSequenceIdModel;

/**
 * @author glinkjoa
 */
public class CachedEntityBuilder extends Builder<CachedEntity, CachedEntityBuilder> {

   private String fact;
   private Date createdAt = new Date();

   @Override
   public CachedEntity build() {
      CachedEntity entity = super.build();
      entity.setId(UUID.randomUUID().toString());
      entity.setFact(fact);
      entity.setCreatedAt(createdAt);
      return entity;
   }

   public CachedEntityBuilder withFact(String fact) {
      this.fact = fact;
      return this;
   }

}
