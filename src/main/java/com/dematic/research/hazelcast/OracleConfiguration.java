/*
 * Copyright (c) Dematic GmbH 2015. All rights reserved. Confidential.
 */
package com.dematic.research.hazelcast;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

/**
 * @author glinkjoa
 */
//@Configuration
//@ConfigurationProperties("oracle")
//@ComponentScan("com.dematic.research.hazelcast.model")
public class OracleConfiguration {

   @NotNull
   private String username;

   @NotNull
   private String password;

   @NotNull
   private String url;

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   @Bean
   DataSource dataSource() throws SQLException {
      OracleDataSource dataSource = new OracleDataSource();
      dataSource.setUser(username);
      dataSource.setPassword(password);
      dataSource.setURL(url);
      dataSource.setImplicitCachingEnabled(true);
      dataSource.setFastConnectionFailoverEnabled(true);
      return dataSource;

   }

}
