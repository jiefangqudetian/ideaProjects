package com.kaishengit.jms.consumer;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class SpringQueueListener3 implements SessionAwareMessageListener {




    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        String text = textMessage.getText();

        if (text.startsWith("Hello")){
            throw new RuntimeException("故意抛出异常"+text);
        }

        /*try{
            if (text.startsWith("Hello")){
                throw new RuntimeException("故意抛出异常"+text);
            }
            textMessage.acknowledge();
        }catch (Exception e){
            System.out.println("故意抛出异常");
            session.recover();
        }*/

    }
}
