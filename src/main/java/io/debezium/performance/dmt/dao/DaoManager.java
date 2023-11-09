/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.performance.dmt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public final class DaoManager {
    @Inject
    Instance<MysqlDao> mysql;
    @Inject
    Instance<PostgresDao> postgres;

    public List<Dao> getEnabledDbs() {
        List<Dao> returnList = new ArrayList<>();
        if (postgres.isResolvable()) {
            returnList.add(postgres.get());
        }
        if (mysql.isResolvable()) {
            returnList.add(mysql.get());
        }
        return returnList;
    }
}
