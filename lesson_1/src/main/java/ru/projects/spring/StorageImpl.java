package ru.projects.spring;

import org.springframework.stereotype.Component;

@Component("storage")
public class StorageImpl implements Storage {

    @Override
    public void getHistory() {
        System.out.println("Получена карточка пациента");
    }
}
