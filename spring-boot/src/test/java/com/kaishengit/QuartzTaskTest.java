package com.kaishengit;

import com.kaishengit.test.MyTimerTask;
import com.kaishengit.test.OpenBrowser;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;

public class QuartzTaskTest {


    @Test
    public void timerTest() throws IOException {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),0,1000);
        System.in.read();
    }

    @Test
    public void conn(){
        try {
            String str = "http://127.0.0.1/movie";
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

    @Test
    public void openBrowserTest(){
        //OpenBrowser.openURL("https://192.168.1.105:8080/movie");
        OpenBrowser.openURL("https://www.baidu.com/");
    }
}
