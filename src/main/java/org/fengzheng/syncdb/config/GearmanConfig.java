package org.fengzheng.syncdb.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fengzheng.syncdb.gearman.GearManWork;
import org.gearman.common.Constants;
import org.gearman.common.GearmanNIOJobServerConnection;
import org.gearman.worker.GearmanWorker;
import org.gearman.worker.GearmanWorkerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tibers on 16/11/6.
 */
@Configuration
public class GearmanConfig {
    private static final Log _logger = LogFactory.getLog(GearmanConfig.class);
    @Value("${gear.man.host}")
    private String gearManHost;

    @Bean
    public GearmanWorker init() {
        _logger.info("init server...");
        GearmanWorker worker = new GearmanWorkerImpl();
        GearmanNIOJobServerConnection conn = new GearmanNIOJobServerConnection(gearManHost, Constants.GEARMAN_DEFAULT_TCP_PORT);
        worker.addServer(conn);
        worker.registerFunction(GearManWork.class);
        worker.work();
        _logger.info("init success....");
        return worker;
    }
}
