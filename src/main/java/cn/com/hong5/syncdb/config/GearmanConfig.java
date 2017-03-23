package cn.com.hong5.syncdb.config;

import cn.com.hong5.syncdb.gearman.GearManWork;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gearman.common.Constants;
import org.gearman.common.GearmanNIOJobServerConnection;
import org.gearman.worker.GearmanWorker;
import org.gearman.worker.GearmanWorkerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Tibers on 16/11/6.
 */
@Component
public class GearmanConfig {
    private static final Log _logger = LogFactory.getLog(GearmanConfig.class);
    @Value("${gear.man.host}")
    private String gearManHost;

    @Bean
    @Async
    public GearmanWorker init() {
        _logger.info("Initializing GearMan");
        GearmanWorker worker = new GearmanWorkerImpl();
        GearmanNIOJobServerConnection conn = new GearmanNIOJobServerConnection(gearManHost, Constants.GEARMAN_DEFAULT_TCP_PORT);
        worker.addServer(conn);
        worker.registerFunction(GearManWork.class);
        worker.work();
        if(!worker.isRunning()){
            _logger.info("Initializing error");
            System.exit(1);
        }
        return worker;
    }
}
