package com.kaishengit.controller;

import com.kaishengit.entity.Movie;
import com.kaishengit.mq.SendJmsMessage;
import com.kaishengit.service.MovieServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsController {

    @Autowired
    private SendJmsMessage sendJmsMessage;
    @Autowired
    private MovieServicce movieServicce;
    @GetMapping("/jms/queue")
    public String sendMessageToQueue(){
        sendJmsMessage.sendMessageToQueue("Hello,"+System.currentTimeMillis());
        return "Send To Queue Success";
    }

    @GetMapping("/jms/topic")
    public String sendMessageToTopic(){
        sendJmsMessage.sendMessageToTopic("Hi,"+System.currentTimeMillis());
        return "Send To Topic Success";
    }

    @GetMapping("/jms/movie")
    public String sendMovieToQueue(){
        Movie movie = movieServicce.findById(101);
        sendJmsMessage.sendMovieToQueue(movie);
        return "Send Movie To Queue Success";
    }
}
