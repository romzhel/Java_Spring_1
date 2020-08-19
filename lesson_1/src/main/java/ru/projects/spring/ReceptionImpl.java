package ru.projects.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("reception")
public class ReceptionImpl implements Reception {
    private Storage storage;
    private Help help;

    @Override
    public void printData() {
        storage.getHistory();
        help.getLocation();
    }

    @Override
    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    @Autowired
    public void setHelp(Help help) {
        this.help = help;
    }
}
