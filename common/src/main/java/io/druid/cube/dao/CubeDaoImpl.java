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
import com.mongodb.client.MongoCollection;
import io.druid.cube.bean.Cube;
import com.google.inject.Inject;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;

/**
 * Created by tuo on 2018/8/3.
 */
public class CubeDaoImpl implements ICubeDao
{

  private MongoCollection<Document> collection;

  @Inject
  public CubeDaoImpl(MongoDB mongoDB)
  {
    this.collection = mongoDB.getDatabase().getCollection("cube");
  }

  @Override
  public Cube Get(String uid, String name)
  {
    BsonDocument filter = new BsonDocument();
    filter.append("appId", new BsonString(uid));
    filter.append("name", new BsonString(name));
    Document document = this.collection.find(filter).limit(1).first();
    if (document != null) {
      return Cube.documentToCube(document);
    }
    return null;
  }

}
