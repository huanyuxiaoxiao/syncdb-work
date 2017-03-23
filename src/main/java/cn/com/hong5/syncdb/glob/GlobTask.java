package cn.com.hong5.syncdb.glob;

import cn.com.hong5.syncdb.handle.SyncHandle;
import cn.com.hong5.syncdb.util.ApplicationUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Tibers on 16/11/7.
 */
@ComponentScan
public class GlobTask {
    private static final GlobTask GLOB_TASK = new GlobTask();
    private ThreadPoolTaskExecutor taskExecutor = ApplicationUtil.getBean(ThreadPoolTaskExecutor.class);
    private ArrayBlockingQueue<byte[]> message = new ArrayBlockingQueue<>(999);

    private GlobTask() {
    }

    public static GlobTask getInstance() {
        return GLOB_TASK;
    }

    public void addTask(byte[] data) {
        message.add(data);
        taskExecutor.execute(new SyncHandle());

    }

    public byte[] takeTask() throws InterruptedException {
        return message.take();
    }
}
