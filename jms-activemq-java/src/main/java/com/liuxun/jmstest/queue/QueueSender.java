package com.liuxun.jmstest.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送者
 */
public class QueueSender {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory
                ("tcp://192.168.1.110:61616");
//                ("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);// 自动确认
        Destination destination = session.createQueue("my-queue");

        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 3; i++) {
//            TextMessage message = session.createTextMessage("message---"+ i);
            MapMessage message = session.createMapMessage();
            message.setStringProperty("extra"+i,"hahaha");
            message.setString("message----"+i,"my map message=="+i);
            Thread.sleep(1000);
            // 通过消息生产者发出消息
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();

    }
}
