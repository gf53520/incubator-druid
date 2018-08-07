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
package io.druid.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import com.google.common.base.Joiner;
import io.druid.cube.bean.Cube;
import io.druid.cube.dao.ICubeDao;
import java.util.Collections;
import java.util.List;

@JsonTypeName("table")
public class TableDataSource implements DataSource
{
  @JsonProperty
  private String name;

  @JsonCreator
  public TableDataSource(@JsonProperty("name") String name)
  {
    this.name = (name == null ? null : name);
  }

  @JsonProperty
  public String getName()
  {
    return name;
  }

  @Override
  public List<String> getNames()
  {
    return Collections.singletonList(name);
  }

  @Override
  public String toString()
  {
    return name;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TableDataSource)) {
      return false;
    }

    TableDataSource that = (TableDataSource) o;

    if (!name.equals(that.name)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode()
  {
    return name.hashCode();
  }

  /*
  dataSource重命名规则：
  比如用户1380750167,cube:cubeTest
  通过API传递过来的datasource name有两种格式
  1. cubeTest
  2. 1380750167:cubeTest
 */
  @Override
  public void rewrite(String defaultUid, ICubeDao iCubeDao)
  {
    if (this.name.contains(":")) {
      //todo 支持权限；
    } else {
      Cube cube = iCubeDao.Get(defaultUid, name);
      this.name = Joiner.on("-").join(defaultUid, this.name, cube.getVersion());
    }
  }
}
