/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.performance.dmt.dao;

import javax.enterprise.context.Dependent;

import org.jboss.logging.Logger;

import io.debezium.performance.dmt.dataSource.DataSourceWrapper;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Dependent
public abstract class AbstractBasicDao implements Dao {

    protected DataSourceWrapper source;
    protected final Logger LOG = Logger.getLogger(getClass());

    public AbstractBasicDao() {
    }

    public AbstractBasicDao(DataSourceWrapper source) {
        this.source = source;
    }

    @Override
    public void execute(String query) {
        LOG.info("The exectuion to database happened");
//        try (Connection conn = source.getConnection();
//                Statement stmt = conn.createStatement()) {
//            stmt.execute(query);
//        }
//        catch (SQLException ex) {
//            LOG.error("Could not insert into database " + query);
//            LOG.error(ex.getMessage());
//        }
    }
}
