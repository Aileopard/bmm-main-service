package com.leo.zu.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author leo-zu
 * @create 2020-10-28 21:47
 */

@Slf4j
@Component
public class PaymentJobHandler extends IJobHandler {

    private static transient Logger logger = LoggerFactory.getLogger(PaymentJobHandler.class);

    @Override
    @XxlJob("PaymentJobHandler")
    public ReturnT<String> execute(String param) throws Exception {
        logger.info("HELLO WORLD!");
        XxlJobLogger.log("hello world");
        return SUCCESS;
    }
}
