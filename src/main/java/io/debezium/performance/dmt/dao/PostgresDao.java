/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.performance.dmt.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


import io.debezium.performance.dmt.dataSource.PostgresDataSource;
import io.quarkus.arc.Unremovable;
import io.quarkus.arc.lookup.LookupIfProperty;

@RequestScoped
@LookupIfProperty(name = "quarkus.datasource.postgresql.enabled", stringValue = "true")
@Unremovable
public final class PostgresDao extends AbstractBasicDao {

    @Inject
    public PostgresDao(PostgresDataSource source) {
        super(source);
    }

    @Override
    public void execute(String query) {
        LOG.info(query + "Executed in Postgres");
    }
}
