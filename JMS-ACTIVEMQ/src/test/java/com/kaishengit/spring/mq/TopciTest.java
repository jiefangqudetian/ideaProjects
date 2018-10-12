package com.kaishengit.spring.mq;


import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-jms-topic3.xml")
public class TopciTest {

    @Autowired
    private JmsTemplate jmsTemplate;
    /*@Autowired
    private Destination destination;
*/
    @Test
    public void sendMessageToTopic() throws IOException, InterruptedException {
        Destination destination1 = new ActiveMQTopic("spring-topic");
        while (true){
            jmsTemplate.send(destination1,new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage("Hello,Spring + JMS--topic" + new Date().getTime());
                }
            });

            Thread.sleep(3000);
        }



        //System.in.read();
    }

    @Test
    public void consumeMessage() throws IOException {
        System.out.println("Spring start...");
        System.in.read();
    }




}
