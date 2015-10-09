/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.model.builder;

import java.util.Date;

import com.dematic.research.hazelcast.model.SimpleSequenceIdModel;

/**
 * @author glinkjoa
 */
public class SimpleSequenceIdModelBuilder extends Builder<SimpleSequenceIdModel, SimpleSequenceIdModelBuilder> {

   private String fact;
   private Date createdAt = new Date();

   @Override
   public SimpleSequenceIdModel build() {
      SimpleSequenceIdModel createdFact = super.build();
      createdFact.setFact(fact);
      createdFact.setCreatedAt(createdAt);
      return createdFact;
   }

   public SimpleSequenceIdModelBuilder withFact(String fact) {
      this.fact = fact;
      return this;
   }

}
