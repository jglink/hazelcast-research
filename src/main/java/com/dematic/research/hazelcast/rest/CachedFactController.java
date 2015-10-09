/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.rest;

import com.dematic.research.hazelcast.model.SimpleFact;
import com.dematic.research.hazelcast.model.builder.SimpleFactBuilder;
import com.dematic.research.hazelcast.service.CachedFactService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author glinkjoa
 */
@RestController
@RequestMapping("/facts")
public class CachedFactController {

   @Autowired
   CachedFactService cachedFactService;

   /**
    * Example: http://localhost:8080/facts/masscreation/100
    *  --> creates 100 new SimpleFacts
    * @param count
    * @return
    */
   @RequestMapping(value="/masscreation/{count}", method=RequestMethod.GET)
   public String massCreation(@PathVariable int count) {
      long begin = System.currentTimeMillis();
      StringBuffer created = new StringBuffer();
      for (int i=0; i<count; i++) {
         SimpleFact fact = new SimpleFactBuilder().withFact(RandomStringUtils.randomAlphanumeric(15)).build();
         cachedFactService.saveFact(fact);
         created.append(fact.toString());
         created.append(System.lineSeparator());
      }
      long duration = System.currentTimeMillis()-begin;
      System.out.println(String.format("##################### duration for %s entries: %s", count, duration));
      return created.toString();
   }

   @RequestMapping(method=RequestMethod.GET)
   public Collection<SimpleFact> getAll() {
      return cachedFactService.findAllFacts();
   }

   @RequestMapping(method=RequestMethod.GET, value="{id}")
   public SimpleFact getById(@PathVariable String id) {
      return cachedFactService.findFact(id);
   }

   @RequestMapping(method=RequestMethod.POST)
   public SimpleFact create(@RequestBody String simpleFact) {
      SimpleFact fact = new SimpleFactBuilder().withFact(simpleFact).build();
      cachedFactService.saveFact(fact);
      return fact;
   }

   @RequestMapping(method=RequestMethod.DELETE, value="{id}")
   public void delete(@PathVariable String id) {
      cachedFactService.deleteFact(id);
   }

   @RequestMapping(method=RequestMethod.PUT, value="{id}")
   public String update(@PathVariable String id, @RequestBody String simpleFact) {
      SimpleFact fact = cachedFactService.findFact(id);
      if (fact != null) {
         fact.setFact(simpleFact);
         cachedFactService.saveFact(fact);
         return fact.getFact();
      }
      return "not-found!";
   }

}
