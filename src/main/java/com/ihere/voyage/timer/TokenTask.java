package com.ihere.voyage.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fengshibo
 * @create 2018-07-06 14:45
 * @desc ${DESCRIPTION}
 **/
@Component
public class TokenTask {
    private static Logger logger = LoggerFactory.getLogger(TokenTask.class);

   // @Scheduled(fixedDelay = 100000)
    public void add() {
        logger.info("我正在执行.." + System.currentTimeMillis());
    }
}
