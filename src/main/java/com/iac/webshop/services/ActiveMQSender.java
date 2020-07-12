package com.iac.webshop.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import static com.iac.webshop.ActiveMQConfig.ACTIVEMQ_QUEUE;

@Service
public class ActiveMQSender {

    private static Logger log = LoggerFactory.getLogger(ActiveMQSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String message) {
        log.info("sending message to queue <" + message + ">");
        jmsTemplate.convertAndSend(ACTIVEMQ_QUEUE, message);
    }
}