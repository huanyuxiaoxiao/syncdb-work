package org.fengzheng.syncdb.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Tibers on 16/11/7.
 */
public class JedisUtil {
    private static final Log _logger= LogFactory.getLog(JedisUtil.class);

    public static void setValue(JedisPool jedisPool, String key, String value) {
        _logger.trace("Begin set data to Redis");
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        _logger.trace("End set data to Redis");
    }
}
