package cn.com.hong5.syncdb.gearman;

import cn.com.hong5.syncdb.glob.GlobTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gearman.client.GearmanJobResult;
import org.gearman.client.GearmanJobResultImpl;
import org.gearman.util.ByteUtils;
import org.gearman.worker.AbstractGearmanFunction;

/**
 * Created by Tibers on 16/11/6.
 */
public class GearManWork extends AbstractGearmanFunction {
    private final Log _logger = LogFactory.getLog(GearManWork.class);
    private final String name = "syncToMongodb";
    private final String resultMessage = "success";

    @Override
    public GearmanJobResult executeFunction() {
        _logger.debug("Success get data");
        GlobTask.getInstance().addTask((byte[]) this.data);
        return new GearmanJobResultImpl(this.jobHandle, true, ByteUtils
                .toUTF8Bytes(resultMessage), new byte[0], new byte[0], 0, 0);
    }

    @Override
    public String getName() {
        return name;
    }
}
