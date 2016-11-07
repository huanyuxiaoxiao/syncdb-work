package org.fengzheng.syncdb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fengzheng.syncdb.util.ApplicationUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 */
@EnableAutoConfiguration
@SpringBootApplication
@Import(ApplicationUtil.class)
public class Main {
    private static final Log _logger = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        _logger.info("Begin run .......");
        SpringApplication.run(Main.class, args);
        _logger.info("Run start success.......");
    }

}