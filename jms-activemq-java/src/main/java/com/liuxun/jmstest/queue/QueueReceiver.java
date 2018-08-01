package com.liuxun.jmstest.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * 消息接收者
 */
public class QueueReceiver {
    public static void main(String[] args) throws Exception{
        ConnectionFactory cf = new ActiveMQConnectionFactory
                ("tcp://192.168.1.110:61616");
//                ("tcp://localhost:61616");
        Connection connection = cf.createConnection();
        connection.start();

        final Enumeration jmsxPropertyNames = connection.getMetaData().getJMSXPropertyNames();
        while (jmsxPropertyNames.hasMoreElements()){
            String name = (String) jmsxPropertyNames.nextElement();
            System.out.println("jmsx name==="+name);

        }

        final Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");
        MessageConsumer consumer = session.createConsumer(destination);

        for (int i = 0; i <3 ; i++) {
//            TextMessage message = (TextMessage) consumer.receive();
            MapMessage message = (MapMessage) consumer.receive();
            System.out.println("收到消息："+message.getString("message----"+i)+", property=="+message.getStringProperty("extra"+i));

        }
        session.commit();
        session.close();
        connection.close();
    }
}
