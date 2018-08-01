package com.liuxun.jmstest.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息接收者
 */
public class PersistentReceiver {
    public static void main(String[] args) throws Exception{
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.110:61616");
        Connection connection = cf.createConnection();
        connection.setClientID("cc1");
        final Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("MyTopic2");
        TopicSubscriber ts = session.createDurableSubscriber(topic,"T1");
        connection.start();
        Message message= ts.receive();
        while(message!= null){
            TextMessage txtMsg = (TextMessage)message;
            System.out.println("收到消息："+txtMsg.getText());
            message = ts.receive(1000L);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
