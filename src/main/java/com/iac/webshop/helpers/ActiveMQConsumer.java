package com.iac.webshop.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Session;

import static com.iac.webshop.ActiveMQConfig.ACTIVEMQ_QUEUE;

@Component
public class ActiveMQConsumer {

    private static Logger log = LoggerFactory.getLogger(ActiveMQConsumer.class);

    @JmsListener(destination = ACTIVEMQ_QUEUE)
    public void receiveMessage(@Payload String payload,
                               @Headers MessageHeaders headers,
                               Message message, Session session) {
        log.info("received <" + payload + ">");
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        log.info("######          Message Details           #####");
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        log.info("headers: " + headers);
        log.info("message: " + message);
        log.info("session: " + session);
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
    }
}