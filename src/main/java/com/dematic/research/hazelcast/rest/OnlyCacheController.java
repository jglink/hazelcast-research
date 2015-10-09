/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.rest;

import java.util.Collection;

import com.dematic.research.hazelcast.model.CachedEntity;
import com.dematic.research.hazelcast.model.builder.CachedEntityBuilder;
import com.dematic.research.hazelcast.service.OnlyCacheService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dematic.research.hazelcast.model.SimpleFact;
import com.dematic.research.hazelcast.model.builder.SimpleFactBuilder;
import com.dematic.research.hazelcast.service.CachedFactService;

/**
 * @author glinkjoa
 */
@RestController
@RequestMapping("/onlycache")
public class OnlyCacheController {

   @Autowired
   OnlyCacheService onlyCacheService;

   /**
    * Example: http://localhost:8080/onlycache/masscreation/100
    *  --> creates 100 new CachedEntity objects
    * @param count
    * @return
    */
   @RequestMapping(value="/masscreation/{count}", method=RequestMethod.GET)
   public String massCreation(@PathVariable int count) {
      long begin = System.currentTimeMillis();
      StringBuffer created = new StringBuffer();
      for (int i=0; i<count; i++) {
         CachedEntity entity = new CachedEntityBuilder().withFact(RandomStringUtils.randomAlphanumeric(15)).build();
         onlyCacheService.saveEntity(entity);
         created.append(entity.toString());
         created.append(System.lineSeparator());
      }
      long duration = System.currentTimeMillis()-begin;
      System.out.println(String.format("##################### duration for %s entries: %s", count, duration));
      return created.toString();
   }

   @RequestMapping(method=RequestMethod.POST)
   public CachedEntity create(@RequestBody String simpleFact) {
      CachedEntity cachedEntity = new CachedEntityBuilder().withFact(simpleFact).build();
      onlyCacheService.saveEntity(cachedEntity);
      return cachedEntity;
   }

   @RequestMapping(method=RequestMethod.GET)
   public Collection<CachedEntity> getAll() {
      return onlyCacheService.findAllEntities();
   }

   @RequestMapping(method=RequestMethod.GET, value="{id}")
   public CachedEntity getById(@PathVariable String id) {
      return onlyCacheService.findEntity(id);
   }

   /**
    * Example: http://localhost:8080/onlycache/search/my-value
    *  --> searches for all map entries with the given value
    * @param name
    * @return
    */
   @RequestMapping(method=RequestMethod.GET, value="/search/{name}")
   public Collection<CachedEntity> search(@PathVariable String name) {
      return onlyCacheService.findEntitiesByName(name);
   }

}
