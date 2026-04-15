package com.lifecycle;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

@Named
@ApplicationScoped
public class LifecycleLogger implements PhaseListener {

    @Override
    public void beforePhase(PhaseEvent e) {
        System.out.println("BEFORE: " + e.getPhaseId());
    }

    @Override
    public void afterPhase(PhaseEvent e) {
        System.out.println("AFTER:  " + e.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}