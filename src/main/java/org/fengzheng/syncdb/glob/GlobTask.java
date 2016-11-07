package org.fengzheng.syncdb.glob;

import org.fengzheng.syncdb.handle.SyncHandle;
import org.fengzheng.syncdb.util.ApplicationUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Tibers on 16/11/7.
 */
@ComponentScan
public class GlobTask {
    private static final GlobTask GLOB_TASK = new GlobTask();
    private ThreadPoolTaskExecutor taskExecutor = ApplicationUtil.getBean(ThreadPoolTaskExecutor.class);
    private JedisPool jedisPool = ApplicationUtil.getBean(JedisPool.class);
    private ArrayBlockingQueue<byte[]> message = new ArrayBlockingQueue<>(999);

    private GlobTask() {
    }

    public static GlobTask getInstance() {
        return GLOB_TASK;
    }

    public void addTask(byte[] data) {
        message.add(data);
        taskExecutor.execute(new SyncHandle(jedisPool));

    }

    public byte[] takeTask() throws InterruptedException {
        return message.take();
    }
}
