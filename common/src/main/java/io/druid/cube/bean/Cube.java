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

package io.druid.cube.bean;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.Document;
import io.druid.java.util.emitter.EmittingLogger;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by tuo on 2018/8/3.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cube
{
  protected static final EmittingLogger log = new EmittingLogger(Cube.class);

  private String name = "";
  private String appId = "";
  private String version = "";
  private boolean deleted = false;

  private Cube()
  {
  }

  public static Cube newInstance()
  {
    return new Cube();
  }

  public static Cube documentToCube(Document document)
  {
    if (document == null) {
      return null;
    }
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(document.toJson(), Cube.class);
    }
    catch (Exception e) {
      log.error(e, "error parse cube document");
    }
    return null;
  }


  public String getName()
  {
    return name;
  }

  public String getAppId()
  {
    return appId;
  }

  public String getVersion()
  {
    return version;
  }

  public boolean isDeleted()
  {
    return deleted;
  }
}
