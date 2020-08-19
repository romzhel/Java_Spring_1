package ru.projects.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("help")
public class HelpIml implements Help {
    private String location;

    public HelpIml(@Value("Кабинет 315") String location) {
        this.location = location;
    }

    @Override
    public void getLocation() {
        System.out.println(location);
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }
}
