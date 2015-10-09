/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author glinkjoa
 */
@Entity
@Table(name = "X_DEMO_SIMPLE_FACT")
public class SimpleFact implements Serializable {

   private String id;
   private String fact;
   private Date createdAt;

   @Id
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getFact() {
      return fact;
   }

   public void setFact(String fact) {
      this.fact = fact;
   }

   @Column(name = "CREATED_AT")
   public Date getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   @Override
   public String toString() {
      return "SimpleFact{" +
            "id='" + id + '\'' +
            ", fact='" + fact + '\'' +
            ", createdAt=" + createdAt +
            '}';
   }

   // DataSerializable:
//   @Override
//   public void writeData(ObjectDataOutput out) throws IOException {
//      out.writeUTF(id);
//      out.writeUTF(fact);
//      out.writeObject(createdAt);
//   }
//
//   @Override
//   public void readData(ObjectDataInput in) throws IOException {
//      id = in.readUTF();
//      fact = in.readUTF();
//      createdAt = in.readObject();
//   }
}
