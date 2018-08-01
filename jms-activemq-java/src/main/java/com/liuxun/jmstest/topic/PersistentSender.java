package com.liuxun.jmstest.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发送者
 */
public class PersistentSender {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.110:61616");
        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);// 自动确认
        Destination destination = session.createTopic("MyTopic2");

        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message222---"+ i);
            // 通过消息生产者发出消息
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();

    }
}
