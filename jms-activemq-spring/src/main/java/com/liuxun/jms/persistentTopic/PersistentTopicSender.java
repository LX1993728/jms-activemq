package com.liuxun.jms.persistentTopic;

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

public class PersistentTopicSender {
   private static JmsTemplate jmsTemplate = null;

   public static void main(String[] args) throws Exception{
       ApplicationContext ctx = new ClassPathXmlApplicationContext(
               "persistent-topic-sender.xml");
       // 获取JmsTemplate对象
       jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
       // 调用方法，发送消息
       jmsTemplate.send(new MessageCreator() {
           // 消息的产生，返回消息发送消息
           public Message createMessage(Session s) throws JMSException {
               TextMessage msg = s
                       .createTextMessage("persistent topic msg ----> Hello World！！！");
               return msg;
           }
       });
       System.out.println("end!");


   }
}
