package com.liuxun.jms.topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;

@Service
public class TopicSender {
   @Autowired
   private JmsTemplate jmsTemplate = null;

   @Autowired
   private ActiveMQTopic destainationTopic;
   private void sendTopic(){
       jmsTemplate.send(destainationTopic,new MessageCreator() {
           @Override
           public Message createMessage(Session session) throws JMSException {
               TextMessage msg = session.createTextMessage("spring message2222===== "+new Date());
               return msg;
           }
       });
   }

   public static void main(String[] args) throws Exception{
       ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-activemq.xml");
       TopicSender queueSender = (TopicSender)ctx.getBean("topicSender");
        queueSender.sendTopic();
   }
}
