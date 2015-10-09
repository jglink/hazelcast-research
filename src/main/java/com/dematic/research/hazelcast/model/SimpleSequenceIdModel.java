/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author glinkjoa
 */
@Entity
@Table(name = "X_SEQ_SIMPLE_FACT")
public class SimpleSequenceIdModel implements Serializable {

   @Id
   @SequenceGenerator(name = "seq", sequenceName = "X_SIMPLE_SEQ")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
   private Long id;

   private String fact;

   @Column(name = "CREATED_AT")
   private Date createdAt;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFact() {
      return fact;
   }

   public void setFact(String fact) {
      this.fact = fact;
   }

   public Date getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   @Override
   public String toString() {
      return "SimpleSequenceIdModel{" +
            "id=" + id +
            ", fact='" + fact + '\'' +
            ", createdAt=" + createdAt +
            '}';
   }
}
