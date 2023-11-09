package io.debezium.performance.dmt.service;

import io.debezium.performance.dmt.async.ExecutorPool;
import io.quarkus.runtime.Startup;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Startup
public class MainService {
    @Inject
    ExecutorPool executorPool;
    private static final Logger LOG = Logger.getLogger(MainService.class);

    public void testServiceMethod() {
        List<String> statements = generateStatements();
        executorPool.setCountDownLatch(statements.size());
        for (String statement: statements) {
            executorPool.executeFunction(dao -> dao.execute(statement));
        }
        LOG.info("Started all threads");
        waitForLastTask();
        LOG.info("All threads finished");
    }

    private void waitForLastTask() {
        try {
            executorPool.getLatch().await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> generateStatements() {
        List<String> statements = new ArrayList<>();
        for(int i = 1; i < 11; i++) {
            statements.add("Statement number " + i);
        }
        return statements;
    }
}
