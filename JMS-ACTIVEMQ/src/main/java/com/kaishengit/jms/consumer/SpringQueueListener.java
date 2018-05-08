package com.kaishengit.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

public class SpringQueueListener implements MessageListener {


    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("queue====>" + textMessage.getText());
            message.acknowledge();
            //System.in.read();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
