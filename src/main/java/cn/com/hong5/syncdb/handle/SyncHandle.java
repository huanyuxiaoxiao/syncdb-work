package cn.com.hong5.syncdb.handle;

import cn.com.hong5.api.entity.user.UserMessageEntity;
import cn.com.hong5.syncdb.util.SerializeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.com.hong5.syncdb.glob.GlobTask;
import cn.com.hong5.syncdb.util.ApplicationUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by Tibers on 16/11/7.
 */
public class SyncHandle extends RunnableUtil implements Runnable {
    private static final Log _logger = LogFactory.getLog(SyncHandle.class);
    private MongoTemplate mongoTemplate = ApplicationUtil.getBean(MongoTemplate.class);

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
        _logger.trace("Begin run task mongodb sync mysql.....");
        try {
            runTask();
        } catch (Exception e) {
            _logger.error("mysql data sync mongodb error", e);
            //TODO 元数据落地
        }
        _logger.trace("End run task mongodb sync mysql.....");
    }

    private void runTask() throws Exception {
        byte[] data = GlobTask.getInstance().takeTask();
        UserMessageEntity userMessageEntity = SerializeUtil.byteToJson(data);
        Integer userId = userMessageEntity.getId();
        if (StringUtils.isEmpty(userId)) {
            return;
        }
        Update update = new Update();
        Map<String, Object> needUpdate = collectNeedUpdateParam(userMessageEntity);
        for (String key : needUpdate.keySet()) {
            update.set(key, needUpdate.get(key));
        }
        mongoTemplate.upsert(Query.query(Criteria.where("_id").is(userId)), update, UserMessageEntity.class);
    }


}
