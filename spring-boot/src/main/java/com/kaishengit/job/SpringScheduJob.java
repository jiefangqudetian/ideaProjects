package com.kaishengit.job;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class SpringScheduJob {

    @Async("taskExecutor")
    @Scheduled(fixedRate = 3000)
    public void fixedRateJob(){
        System.out.println("fixedRateJob-->"+Thread.currentThread().getName()+"-->"+System.currentTimeMillis());
    }

    //@Scheduled(cron = "0/5 * * * * ?")
    public void CronJob(){
        System.out.println("CronJob-->"+Thread.currentThread().getName()+"-->"+System.currentTimeMillis());
    }

    //@Scheduled(fixedRate = 3000)
    public void fixedDelayJob(){
        System.out.println("fixedDelaJob-->"+Thread.currentThread().getName()+"-->"+System.currentTimeMillis());
    }

    //@Scheduled(fixedRate = 3000)
    public void conn(){
        try {
            String str = "http://192.168.1.105:8080/movie/121";
            //String str = "https://www.baidu.com/";
            URL url = new URL(str);
            //得到connection对象。
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //连接
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = connection.getInputStream();
                //获取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null){
                    System.out.println(line);
                }
                reader.close();
                //该干的都干完了,记得把连接断了
                connection.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
