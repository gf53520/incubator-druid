/*
 * Licensed to Metamarkets Group Inc. (Metamarkets) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Metamarkets licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.druid.cube.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.druid.cube.common.MongoConfig;
import javax.inject.Inject;

/**
 * Created by tuo on 2018/8/3.
 */
public class MongoDB
{

  private final MongoDatabase database;

  @Inject
  public MongoDB(MongoConfig mongoConfig)
  {
    MongoClient client = new MongoClient(mongoConfig.getUrl());
    this.database = client.getDatabase(mongoConfig.getDatabase());
  }

  public MongoDatabase getDatabase()
  {
    return database;
  }
}
