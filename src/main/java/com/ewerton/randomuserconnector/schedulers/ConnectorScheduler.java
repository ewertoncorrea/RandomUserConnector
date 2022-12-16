package com.ewerton.randomuserconnector.schedulers;

import com.ewerton.randomuserconnector.controllers.RandomUserAPIController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConnectorScheduler {

    private static final Logger log = LoggerFactory.getLogger(ConnectorScheduler.class);

    private RandomUserAPIController randomUserAPIController;

    @Autowired
    ConnectorScheduler(RandomUserAPIController randomUserAPIController) {
        this.randomUserAPIController = randomUserAPIController;
    }

    @Scheduled(fixedRateString = "${period}")
    public void reportCurrentTime() {
        randomUserAPIController.getUsers();
    }
}
