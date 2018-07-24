package com.liuxun.jms.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;

@Service
public class QueueSender {
   @Autowired
   private JmsTemplate jmsTemplate = null;

   public static void main(String[] args) throws Exception{
       ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-activemq.xml");
       QueueSender queueSender = (QueueSender)ctx.getBean("queueSender");
       queueSender.jmsTemplate.send(new MessageCreator() {
           @Override
           public Message createMessage(Session session) throws JMSException {
               TextMessage msg = session.createTextMessage("spring message===== "+new Date());
               return msg;
           }
       });

   }
}
