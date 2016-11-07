package org.fengzheng.syncdb.handle;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fengzheng.syncdb.glob.GlobTask;
import org.fengzheng.syncdb.util.JedisUtil;
import org.fengzheng.syncdb.util.SerializeUtil;
import redis.clients.jedis.JedisPool;

/**
 * Created by Tibers on 16/11/7.
 */
public class SyncHandle implements Runnable {
    private static final Log _logger = LogFactory.getLog(SyncHandle.class);
    private JedisPool jedisPool;

    public SyncHandle(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        _logger.trace("Begin run task redis sync mysql.....");
        try {
            runTask();
        } catch (Exception e) {
            _logger.error("mysql data sync redis error", e);
            //TODO 元数据落地
        }
        _logger.trace("End run task redis sync mysql.....");
    }

    private void runTask() throws Exception {
        byte[] data = GlobTask.getInstance().takeTask();
        JSONObject jsonObject = SerializeUtil.byteToJson(data);
        JedisUtil.setValue(jedisPool, jsonObject.get("id").toString(), jsonObject.toJSONString());
    }
}
