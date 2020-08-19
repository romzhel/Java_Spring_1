package ru.projects.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    /*@Bean(name = "storage")
    public Storage storage() {
        return new StorageImpl();
    }*/

    /*@Bean(name = "help")
    public Help help(@Value("Кабинет 315") String location){
        return new HelpIml(location);
    }*/

    /*@Bean(name = "reception")
    public Reception reception(Storage storage, Help help){
        Reception reception = new ReceptionImpl();
        reception.setStorage(storage);
        reception.setHelp(help);
        return reception;
    }*/
}
