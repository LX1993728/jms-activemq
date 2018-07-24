package com.liuxun.jms.persistentTopic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class PersistentTopicListener2 implements MessageListener {
    @Override
    public void onMessage(Message message){
        TextMessage msg = (TextMessage) message;
        try {
            System.out.println("Message Listener2 ==== "+msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
