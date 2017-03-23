package cn.com.hong5.syncdb;

import cn.com.hong5.syncdb.util.ApplicationUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

/**
 * Hello world!
 */
@EnableAutoConfiguration
@SpringBootApplication
@Import(ApplicationUtil.class)
@EnableAsync
public class Main {
    private static final Log _logger = LogFactory.getLog(Main.class);

    public static void main(String[] args) throws IOException {
        _logger.info("Begin run .......");
        SpringApplication.run(Main.class, args);
        _logger.info("Run start success.......");
    }

}
