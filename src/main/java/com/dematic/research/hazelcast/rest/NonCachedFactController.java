/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.rest;

import com.dematic.research.hazelcast.model.SimpleFact;
import com.dematic.research.hazelcast.model.SimpleSequenceIdModel;
import com.dematic.research.hazelcast.model.builder.SimpleFactBuilder;
import com.dematic.research.hazelcast.model.builder.SimpleSequenceIdModelBuilder;
import com.dematic.research.hazelcast.service.NonCachedFactService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author glinkjoa
 */
@RestController
@RequestMapping("/noncached")
public class NonCachedFactController {

   @Autowired
   NonCachedFactService nonCachedFactService;

   /**
    * Example: http://localhost:8080/noncached/masscreation/uuid/100
    *  --> creates 100 new SimpleFacts with UUID generation
    * @param count
    * @return
    */
   @RequestMapping(value="/masscreation/uuid/{count}", method=RequestMethod.GET)
   public String uuidCreation(@PathVariable int count) {
      long begin = System.currentTimeMillis();
      StringBuffer created = new StringBuffer();
      for (int i=0; i<count; i++) {
         SimpleFact fact = new SimpleFactBuilder().withFact(RandomStringUtils.randomAlphanumeric(15)).build();
         nonCachedFactService.saveFact(fact);
         created.append(fact.toString());
         created.append(System.lineSeparator());
      }
      long duration = System.currentTimeMillis()-begin;
      System.out.println(String.format("##################### duration for %s entries: %s", count, duration));
      return created.toString();
   }


   /**
    * Example: http://localhost:8080/noncached/masscreation/seq/100
    *  --> creates 100 new SimpleSequenceIdModels with ID generation per Oracle sequence
    * @param count
    * @return
    */
   @RequestMapping(value="/masscreation/seq/{count}", method=RequestMethod.GET)
   public String sequenceCreation(@PathVariable int count) {
      long begin = System.currentTimeMillis();
      StringBuffer created = new StringBuffer();
      for (int i=0; i<count; i++) {
         SimpleSequenceIdModel seqIdModel = new SimpleSequenceIdModelBuilder().withFact(RandomStringUtils.randomAlphanumeric(15)).build();
         nonCachedFactService.saveSequenceIdModel(seqIdModel);
         created.append(seqIdModel.toString());
         created.append(System.lineSeparator());
      }
      long duration = System.currentTimeMillis()-begin;
      System.out.println(String.format("##################### duration for %s entries: %s", count, duration));
      return created.toString();
   }

}
