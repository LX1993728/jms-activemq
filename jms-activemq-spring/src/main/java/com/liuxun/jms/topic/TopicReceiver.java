package com.liuxun.jms.topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class TopicReceiver {
   @Autowired
   private JmsTemplate jmsTemplate;

    @Autowired
    private ActiveMQTopic destainationTopic;

   public void reveiveTopic(){

       String msg = (String)jmsTemplate.receiveAndConvert(destainationTopic);
       System.out.println("Mesage Receive===== "+msg);
   }

   public static void main(String[] args) throws Exception{
       ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-activemq.xml");
       TopicReceiver queueReceiver = (TopicReceiver)ctx.getBean("topicReceiver");
       queueReceiver.reveiveTopic();
   }
}
