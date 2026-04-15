/*
 * JSF-Mastery - Beginner-Friendly JSF 2.3 + PrimeFaces 12 Project
 * 
 * This is a BACKING BEAN (also called Managed Bean).
 * It connects your XHTML page to Java logic.
 * 
 * HOW IT WORKS:
 * 1. User types in the input field on index.xhtml
 * 2. JSF sends the value to setName() below
 * 3. User clicks Submit → submit() method runs
 * 4. Page updates with the result
 * 
 * ANNOTATIONS EXPLAINED:
 * @Named         → Makes this bean accessible in XHTML as #{formBean}
 * @RequestScoped  → NEW bean instance created for EACH HTTP request
 *                  (Other options: @ViewScoped, @SessionScoped, @ApplicationScoped)
 */
package com.beans;

// THESE IMPORTS ARE FOR JAVA EE 8 (Tomcat 9)
// If using Tomcat 10+, change javax.* to jakarta.*
import javax.annotation.PostConstruct;   // Runs after bean is created
import javax.annotation.PreDestroy;      // Runs before bean is destroyed
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named           // Default name = "formBean" (first letter lowercase)
@RequestScoped   // Lives for one HTTP request only
public class FormBean {

    // This field is bound to the input field in index.xhtml
    // When user types, JSF calls setName() automatically
    private String name;

    /*
     * @PostConstruct runs IMMEDIATELY after the bean is created
     * and all dependencies are injected.
     * Use this for initialization logic.
     * 
     * JSF Phase: Called during RENDER_RESPONSE phase
     */
    @PostConstruct
    public void init() {
        System.out.println(">>> @PostConstruct - Bean created and initialized");
    }

    /*
     * @PreDestroy runs JUST BEFORE the bean is destroyed.
     * For @RequestScoped, this happens at the end of the request.
     * Use this for cleanup (closing connections, etc.).
     * 
     * JSF Phase: Called after RENDER_RESPONSE completes
     */
    @PreDestroy
    public void cleanup() {
        System.out.println(">>> @PreDestroy - Bean destroyed");
    }

    /*
     * This method is called when user clicks the Submit button.
     * The action="#{formBean.submit}" in index.xhtml maps to this method.
     * 
     * Return value:
     * - return null       → Stay on same page (most common for demos)
     * - return "success"  → Navigate to success.xhtml
     * - return "page?faces-redirect=true" → Redirect to page.xhtml
     * 
     * JSF Phase: Called during INVOKE_APPLICATION (Phase 5)
     */
    public String submit() {
        System.out.println(">>> submit() called - User entered: " + name);
        return null; // Stay on the same page
    }

    /*
     * GETTER - Called by #{formBean.name} in XHTML to DISPLAY the value.
     * JSF calls this during RENDER_RESPONSE phase.
     */
    public String getName() {
        return name;
    }

    /*
     * SETTER - Called by JSF to SET the value from the input field.
     * JSF calls this during UPDATE_MODEL_VALUES phase (Phase 4).
     * The parameter name MUST match the input field's value binding.
     */
    public void setName(String name) {
        System.out.println(">>> setName() called with: " + name);
        this.name = name;
    }
}
