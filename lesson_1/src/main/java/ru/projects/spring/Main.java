package ru.projects.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        /*ru.projects.spring.ReceptionImpl reception = new ru.projects.spring.ReceptionImpl();
        reception.setStorage(new ru.projects.spring.StorageImpl());
        reception.setHelp(new ru.projects.spring.HelpIml());
        reception.printData();*/

        /*ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        ReceptionImpl reception = context.getBean("reception", ReceptionImpl.class);
        reception.printData();*/

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Reception reception = context.getBean("reception", ReceptionImpl.class);
        reception.printData();
    }


}
