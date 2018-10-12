package com.kaishengit.mq;

import com.kaishengit.entity.Movie;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

//@Component
public class MyJmsListener {

    @JmsListener(destination = "movie-queue")
    public void getUserFromQueue(Movie movie){
        System.out.println("Movie: " + movie);
    }

    @JmsListener(destination = "springboot-queue")
    public void queueListener(TextMessage message){
        try {
            System.out.println("Queue Listener: " + message.getText());
            message.acknowledge();

        } catch (JMSException e) {
            e.printStackTrace();
        }
        //throw new RuntimeException("故意抛出异常");
    }

    @JmsListener(destination = "springboot-topic",containerFactory = "topicListenerContainerFactory")
    public void topicListener(String message){
        System.out.println("Topic Listener: " + message);
    }
}
