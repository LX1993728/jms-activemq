package com.liuxun.jms.persistentTopic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class PersistentTopicListener1 implements MessageListener {
    @Override
    public void onMessage(Message message){
        TextMessage msg = (TextMessage) message;
        try {
            System.out.println("Message Listener1 ==== "+msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
