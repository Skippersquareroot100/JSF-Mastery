/*
 * JSF Lifecycle PhaseListener - Logs ALL 6 Phases of JSF Request
 * 
 * WHAT IS A PHASE LISTENER?
 * A PhaseListener is like a security camera for JSF.
 * It watches every phase of the JSF lifecycle and logs what happens.
 * 
 * WHY IS THIS USEFUL FOR BEGINNERS?
 * Most beginners don't understand when bean methods are called.
 * This listener shows you EXACTLY when each phase runs.
 * 
 * HOW TO USE:
 * 1. Open terminal: tail -f /path/to/tomcat/logs/catalina.out
 * 2. Open the page in browser
 * 3. Submit the form
 * 4. Watch the console - you'll see all 6 phases!
 * 
 * THE 6 PHASES OF JSF LIFECYCLE:
 * 
 * On Initial Page Load (GET request):
 *   Phase 1: RESTORE_VIEW
 *   Phase 6: RENDER_RESPONSE
 *   (Only 2 phases because no data to process)
 * 
 * On Form Submit (POST request):
 *   Phase 1: RESTORE_VIEW        → Rebuild component tree from saved state
 *   Phase 2: APPLY_REQUEST_VALUES → Extract submitted values from HTTP request
 *   Phase 3: PROCESS_VALIDATIONS  → Validate all input fields
 *   Phase 4: UPDATE_MODEL_VALUES  → Update bean properties (setName() called here!)
 *   Phase 5: INVOKE_APPLICATION   → Execute action methods (submit() called here!)
 *   Phase 6: RENDER_RESPONSE      → Generate HTML and send back to browser
 */
package com.beans;

// Import JSF phase event classes
import javax.faces.event.PhaseEvent;    // Event fired before/after each phase
import javax.faces.event.PhaseId;       // Enum of all 6 phase IDs
import javax.faces.event.PhaseListener; // Interface we must implement

/**
 * This class listens to ALL JSF lifecycle phases and logs them.
 * Registered in: WEB-INF/faces-config.xml
 */
public class LifecyclePhaseListener implements PhaseListener {

    /**
     * Which phases do we want to listen to?
     * 
     * Options:
     * - PhaseId.ANY_PHASE        → Listen to ALL 6 phases (what we use)
     * - PhaseId.RESTORE_VIEW     → Only listen to Phase 1
     * - PhaseId.RENDER_RESPONSE  → Only listen to Phase 6
     * - etc.
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE; // Listen to ALL phases
    }

    /**
     * Called BEFORE each phase starts.
     * Use this to see what's about to happen.
     */
    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("========================================");
        System.out.println("▶️ BEFORE Phase: " + event.getPhaseId());
        System.out.println("========================================");
    }

    /**
     * Called AFTER each phase completes.
     * Use this to see what just happened.
     */
    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("========================================");
        System.out.println("⏹️ AFTER Phase: " + event.getPhaseId());
        System.out.println("========================================");
    }
}
