/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.performance.dmt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Singleton;


@Singleton
public final class DaoManager {
    List<Dao> enabledDbs;

    public DaoManager() {
        enabledDbs = new ArrayList<>();
        if (CDI.current().select(PostgresDao.class).isResolvable()) {
            enabledDbs.add(CDI.current().select(PostgresDao.class).get());
        }
        if (CDI.current().select(MysqlDao.class).isResolvable()) {
            enabledDbs.add(CDI.current().select(MysqlDao.class).get());
        }
    }

    public List<Dao> getEnabledDbs() {
        return enabledDbs;
    }
}
