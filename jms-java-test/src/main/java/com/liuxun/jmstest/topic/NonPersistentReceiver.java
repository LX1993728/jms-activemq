package com.liuxun.jmstest.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * 消息接收者
 */
public class NonPersistentReceiver {
    public static void main(String[] args) throws Exception{
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.110:61616");
        Connection connection = cf.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("MyTopic");
        MessageConsumer consumer = session.createConsumer(destination);
        Message message= consumer.receive();
        while(message!= null){
            TextMessage txtMsg = (TextMessage)message;
            System.out.println("收到消息："+txtMsg.getText());
            message = consumer.receive(1000L);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
