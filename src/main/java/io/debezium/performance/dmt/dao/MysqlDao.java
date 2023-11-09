/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.performance.dmt.dao;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;


import io.debezium.performance.dmt.dataSource.MysqlDataSource;
import io.quarkus.arc.Unremovable;
import io.quarkus.arc.lookup.LookupIfProperty;

@Dependent
@LookupIfProperty(name = "quarkus.datasource.mysql.enabled", stringValue = "true")
@Unremovable
public final class MysqlDao extends AbstractBasicDao {

    @Inject
    public MysqlDao(MysqlDataSource source) {
        super(source);
    }

    @Override
    public void execute(String query) {
        LOG.info(query + "Executed in Postgres");
    }
}
