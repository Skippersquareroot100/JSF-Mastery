package com.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class FormBean {

    private String name;

    @PostConstruct
    public void init() {
        System.out.println(">>> @PostConstruct called (Bean created)");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println(">>> @PreDestroy called (Bean destroyed)");
    }

    public String submit() {
        System.out.println(">>> submit() method executed - User entered: " + name);
        return null; // Stay on same page
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println(">>> setName() called with value: " + name);
        this.name = name;
    }
}
