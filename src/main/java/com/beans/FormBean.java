package com.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Named
@RequestScoped
public class FormBean {

    private String name;

    @PostConstruct
    public void init() {
        System.out.println("=== JSF Lifecycle: @PostConstruct (Apply Request Values phase) ===");
    }

    public String submit() {
        System.out.println("=== JSF Lifecycle: submit() called (Invoke Application phase) ===");
        System.out.println("Name value: " + name);
        return null; // Stay on same page
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("=== JSF Lifecycle: @PreDestroy (Render Response phase completed) ===");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Setter called with: " + name);
        this.name = name;
    }
}
