package com.beans;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * JSF PhaseListener - Logs all 6 phases of the JSF Lifecycle
 */
public class LifecyclePhaseListener implements PhaseListener {

    @Override
    public PhaseId getPhaseId() {
        // Listen to ALL phases
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("========================================");
        System.out.println(">>> BEFORE Phase: " + event.getPhaseId());
        System.out.println("========================================");
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("========================================");
        System.out.println("<<< AFTER Phase: " + event.getPhaseId());
        System.out.println("========================================");
    }
}
