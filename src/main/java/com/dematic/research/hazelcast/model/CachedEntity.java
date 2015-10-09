/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast.model;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.IOException;
import java.util.Date;

/**
 * @author glinkjoa
 */
@Entity
@Table(name = "X_DEMO_SIMPLE_FACT")
public class CachedEntity implements DataSerializable {

   @Id
   private String id;
   private String fact;
   @Column(name = "CREATED_AT")
   private Date createdAt;

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

   public Date getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   @Override
   public void writeData(ObjectDataOutput out) throws IOException {
      out.writeUTF(id);
      out.writeUTF(fact);
      out.writeObject(createdAt);
   }

   @Override
   public void readData(ObjectDataInput in) throws IOException {
      id = in.readUTF();
      fact = in.readUTF();
      createdAt = in.readObject();
   }

   @Override
   public String toString() {
      return "CachedEntity{" +
            "id='" + id + '\'' +
            ", fact='" + fact + '\'' +
            ", createdAt=" + createdAt +
            '}';
   }
}
