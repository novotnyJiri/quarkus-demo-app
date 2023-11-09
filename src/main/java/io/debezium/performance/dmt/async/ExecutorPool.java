package io.debezium.performance.dmt.async;

import io.debezium.performance.dmt.dao.Dao;
import io.debezium.performance.dmt.dao.DaoManager;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@ApplicationScoped
@Startup
public class ExecutorPool {
    private final ExecutorService pool;
    private final BlockingQueue<RunnableTask> runnableTasksQueue;
    private CountDownLatch latch;


    @Inject
    public ExecutorPool(DaoManager manager) {
        pool = Executors.newFixedThreadPool(10);
        latch = new CountDownLatch(0);
        runnableTasksQueue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 10; i++) {
            runnableTasksQueue.add(new RunnableTask(manager.getEnabledDbs()));
        }
    }

    public void executeFunction(Consumer<Dao> daoFunction) {
        RunnableTask task;
        try {
            task = runnableTasksQueue.take();
        } catch (InterruptedException e) {
            return;
        }
        pool.submit(() -> {
            task.setDaoFunctionAndExecute(daoFunction);
            try {
                runnableTasksQueue.put(task);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
        });
    }

    public void setCountDownLatch(int number) {
        latch = new CountDownLatch(number);
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
