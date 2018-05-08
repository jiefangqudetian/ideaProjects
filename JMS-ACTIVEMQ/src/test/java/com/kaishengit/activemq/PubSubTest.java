package com.kaishengit.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class PubSubTest {


    @Test
    public void createTopic() throws JMSException {
        //1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //2. 创建连接 并 开启
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3. 创建Session(是否需要事务，消息的签收模式：AUTO_ACKNOWLEDGE自动签收)
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建消息主题
        Topic topic = session.createTopic("hello-topic");
        //5.创建消息生产者
        MessageProducer producer = session.createProducer(topic);
        //6.发送消息
        TextMessage textMessage = session.createTextMessage("hello,topic-1");
        producer.send(textMessage);
        //7.释放资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void consumeTopic() throws JMSException, IOException {
        //1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //2. 创建连接 并 开启
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3. 创建Session(是否需要事务，消息的签收模式：AUTO_ACKNOWLEDGE自动签收)
        final Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //4. 创建消息主题
        Topic topic = session.createTopic("hello-topic");
        //5. 创建消息消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //6.配置消息的监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;

                try {
                    String text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();

        //7.释放资源
        consumer.close();
        session.close();
        connection.close();
    }
}
