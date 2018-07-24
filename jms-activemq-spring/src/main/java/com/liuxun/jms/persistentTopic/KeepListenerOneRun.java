package com.liuxun.jms.persistentTopic;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KeepListenerOneRun {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("persistent-topic-listener1.xml");
        while(true) {
        }
    }
}
