package org.fengzheng.syncdb.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Tibers on 16/11/7.
 */
@Configuration
public class JedisConfig {
    private final Log _logger = LogFactory.getLog(JedisConfig.class);
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private int database;
    @Value("${redis.pool.maxTotal}")
    private int maxTotal;
    @Value("${redis.pool.maxIdle}")
    private int maxIdle;

    public JedisPoolConfig iniConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool initPool() {
        _logger.info("Initializing Jedis");
        JedisPoolConfig config = iniConfig();
        JedisPool jedisPool = new JedisPool(config, host, port, timeout, password, database);
        _logger.info("Initializing Jedis 'Jedis'");
        return jedisPool;
    }
}
