package org.fengzheng.syncdb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by Tibers on 16/11/7.
 */
@Configuration
public class ThreadPoolConfig {
    @Value("${thread.core.size}")
    private int coreSize;
    @Value("${thread.max.size}")
    private int maxSize;
    @Value("${thread.keep.alive}")
    private int keepAlive;

    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor initPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setKeepAliveSeconds(keepAlive);
        executor.setThreadNamePrefix("syncdb");
        executor.initialize();
        return executor;

    }

}
