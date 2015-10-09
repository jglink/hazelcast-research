/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.model.builder;

import com.dematic.research.hazelcast.model.SimpleFact;

import java.util.Date;
import java.util.UUID;

/**
 * @author glinkjoa
 */
public class SimpleFactBuilder extends Builder<SimpleFact, SimpleFactBuilder> {

   private String id = UUID.randomUUID().toString();
   private String fact;
   private Date createdAt = new Date();

   @Override
   public SimpleFact build() {
      SimpleFact createdFact = super.build();
      createdFact.setId(id);
      createdFact.setFact(fact);
      createdFact.setCreatedAt(createdAt);
      return createdFact;
   }

   public SimpleFactBuilder withFact(String fact) {
      this.fact = fact;
      return this;
   }

}
