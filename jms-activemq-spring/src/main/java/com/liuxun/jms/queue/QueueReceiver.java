package com.liuxun.jms.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class QueueReceiver {
   @Autowired
   private JmsTemplate jmsTemplate;

   public static void main(String[] args) throws Exception{
       ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-activemq.xml");
       QueueReceiver queueReceiver = (QueueReceiver)ctx.getBean("queueReceiver");
       String msg = (String) queueReceiver.jmsTemplate.receiveAndConvert();
       System.out.println("msg= "+msg);
   }
}
