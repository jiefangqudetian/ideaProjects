package com.kaishengit.spring.mq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-jms-queque4.xml")
public class QueueTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void sendMessageToQueue() throws IOException, InterruptedException {
//        while (true){
//            jmsTemplate.send(new MessageCreator() {
//                public Message createMessage(Session session) throws JMSException {
//                    return session.createTextMessage("Hello,Spring + JMS -" + new Date().getTime());
//                }
//            });
//
//            Thread.sleep(3000);
//        }

        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("Hello,Spring + JMS -" + new Date().getTime());
            }
        });

        System.in.read();
    }

    @Test
    public void consumeMessage() throws IOException {
        System.out.println("Spring start...");
        System.in.read();
    }




}
