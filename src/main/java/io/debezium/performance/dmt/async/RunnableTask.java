package io.debezium.performance.dmt.async;

import io.debezium.performance.dmt.dao.Dao;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.function.Consumer;

public class RunnableTask implements Runnable {
    List<Dao> dbs;
    Consumer<Dao> daoFunction;

    private static final Logger LOG = Logger.getLogger(RunnableTask.class);


    public RunnableTask(List<Dao> dbs) {
        this.dbs = dbs;
    }

    @Override
    public void run()
    {
        for (Dao dao : dbs) {
            LOG.info("Trying to execute query to dao -- EXPECTED TO PRINT");
            daoFunction.accept(dao);
            LOG.info("Successful execution to dao -- EXPECTED TO FAIL PRINT");
        }
    }

    public Consumer<Dao> getDaoFunction() {
        return daoFunction;
    }

    public void setDaoFunction(Consumer<Dao> daoFunction) {
        this.daoFunction = daoFunction;
    }

    public void setDaoFunctionAndExecute(Consumer<Dao> daoFunction) {
        setDaoFunction(daoFunction);
        run();
    }
}
