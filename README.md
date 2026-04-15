# JSF-Mastery - Complete JSF 2.3 Project Template for Tomcat 9

A complete, working Java EE 8 (JSF 2.3 + PrimeFaces 12) web application configured for **Apache Tomcat 9**.

---

## 🎯 Why this project?
If you are a beginner struggling to set up a Java EE / JSF project, this is for you.
- **Pull & Play**: Just clone and run. No complex setup.
- **Educational Comments**: Every file (`pom.xml`, `web.xml`, `index.xhtml`, `FormBean.java`) is loaded with comments explaining **WHY** we use each part.
- **Lifecycle Visualization**: Includes a PhaseListener that logs all 6 JSF phases to your console so you can see the magic happen.
- **PrimeFaces Ready**: Pre-configured with PrimeFaces 12 for beautiful UI components.

---

## 🚀 Quick Start

### Prerequisites
- **Java 8+** (JDK 8, 11, or 17)
- **Apache Maven 3.6+**
- **Apache Tomcat 9.x**

### Run the Project
```bash
# 1. Build the WAR
mvn clean package

# 2. Copy to Tomcat
cp target/JSF-Lifecycle-Lab-1.0-SNAPSHOT.war /path/to/tomcat/webapps/jsf-mastery.war

# 3. Start Tomcat
/path/to/tomcat/bin/startup.sh

# 4. Open in browser
http://localhost:8080/jsf-mastery/index.xhtml
```

---

## 📁 Project Structure

```
JSF-Mastery/
│
├── pom.xml                          # Maven build config (Java EE 8 deps)
├── .gitignore                       # Ignores target/ and IDE files
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── beans/
│   │   │           ├── FormBean.java              # CDI Managed Bean (@Named + @RequestScoped)
│   │   │           └── LifecyclePhaseListener.java # JSF PhaseListener (logs all 6 phases)
│   │   │
│   │   └── webapp/
│   │       ├── index.xhtml                        # Main JSF page
│   │       │
│   │       └── WEB-INF/
│   │           ├── web.xml                        # Servlet config (FacesServlet, welcome file)
│   │           ├── beans.xml                      # CDI config (bean discovery mode)
│   │           └── faces-config.xml               # JSF config (PhaseListener registration)
│   │
│   └── test/
│       └── java/                                  # Test classes (empty)
│
└── target/                                        # Build output (auto-generated)
    ├── JSF-Lifecycle-Lab-1.0-SNAPSHOT.war         # Deployable WAR file
    ├── classes/                                   # Compiled .class files
    └── maven-status/                              # Maven build metadata
```

### What Each File Does

| File | Purpose |
|------|---------|
| `pom.xml` | Declares Java EE 8 dependencies, Java version, and build plugins |
| `web.xml` | Registers `FacesServlet` for `*.xhtml` URLs and sets welcome file |
| `beans.xml` | Enables CDI dependency injection with `bean-discovery-mode="all"` |
| `faces-config.xml` | Registers custom `PhaseListener` for JSF lifecycle logging |
| `FormBean.java` | Backing bean with `@Named` and `@RequestScoped` annotations |
| `LifecyclePhaseListener.java` | Logs all 6 JSF lifecycle phases before/after execution |
| `index.xhtml` | JSF page with PrimeFaces components |

---

## 🏗️ How to Create a Proper Java EE 8 + JSF + PrimeFaces Project

### Step 1: Create Maven Project

```bash
mvn archetype:generate \
  -DgroupId=com.example \
  -DartifactId=my-jsf-app \
  -DarchetypeArtifactId=maven-archetype-webapp \
  -DinteractiveMode=false
```

### Step 2: Configure pom.xml (CRITICAL)

```xml
<project>
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <failOnMissingWebXml>false</failOnMissingWebXml>
    </properties>

    <dependencies>
        <!-- Servlet API (provided by Tomcat) -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>

        <!-- JSF 2.3 (Mojarra) -->
        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>jakarta.faces</artifactId>
            <version>2.3.21</version>
        </dependency>

        <!-- PrimeFaces 12 (Java EE / javax version - NO jakarta classifier) -->
        <dependency>
            <groupId>org.primefaces</groupId>
            <artifactId>primefaces</artifactId>
            <version>12.0.0</version>
        </dependency>

        <!-- CDI Weld for Tomcat -->
        <dependency>
            <groupId>org.jboss.weld.servlet</groupId>
            <artifactId>weld-servlet-core</artifactId>
            <version>3.1.9.Final</version>
        </dependency>

        <!-- CDI API (provided) -->
        <dependency>
            <groupId>javax.enterprise</groupId>
            <artifactId>cdi-api</artifactId>
            <version>2.0.SP1</version>
            <scope>provided</scope>
        </dependency>

        <!-- EL API -->
        <dependency>
            <groupId>javax.el</groupId>
            <artifactId>javax.el-api</artifactId>
            <version>3.0.0</version>
            <scope>provided</scope>
        </dependency>

        <!-- JSTL -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>
</project>
```

### Step 3: Create web.xml

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         version="4.0">

    <context-param>
        <param-name>javax.faces.PROJECT_STAGE</param-name>
        <param-value>Development</param-value>
    </context-param>

    <servlet>
        <servlet-name>Faces Servlet</servlet-name>
        <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>*.xhtml</url-pattern>
    </servlet-mapping>

    <welcome-file-list>
        <welcome-file>index.xhtml</welcome-file>
    </welcome-file-list>
</web-app>
```

### Step 4: Create beans.xml

```xml
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                           http://xmlns.jcp.org/xml/ns/javaee/beans_2_0.xsd"
       version="2.0"
       bean-discovery-mode="all">
</beans>
```

### Step 5: Create faces-config.xml

```xml
<faces-config version="2.3"
              xmlns="http://xmlns.jcp.org/xml/ns/javaee"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                                  http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_3.xsd">
</faces-config>
```

### Step 6: Create index.xhtml

```xml
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:p="http://primefaces.org/ui">

    <h:head>
        <title>My JSF App</title>
    </h:head>

    <h:body>
        <h1>Welcome to JSF + PrimeFaces</h1>

        <h:form>
            <p:outputLabel value="Name:" for="name" />
            <p:inputText id="name" value="#{formBean.name}" required="true" />
            <p:message for="name" />
            <br/><br/>
            <p:commandButton value="Submit" action="#{formBean.submit}" update="@form" />
        </h:form>
    </h:body>
</html>
```

### Step 7: Create Managed Bean

```java
package com.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class FormBean {

    private String name;

    public String submit() {
        System.out.println("Submitted: " + name);
        return null; // Stay on same page
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

---

## ⚠️ Critical Rules - NEVER Break These

### 1. NEVER Mix javax.* and jakarta.*
```
❌ WRONG: Mix javax.servlet with jakarta.faces
✅ CORRECT: All javax.* or all jakarta.* - NEVER both
```

### 2. Tomcat Version Dictates Everything
| Server | Must Use | Packages | JSF Version | PrimeFaces Version |
|--------|----------|----------|-------------|-------------------|
| **Tomcat 9** | Java EE 8 | `javax.*` | 2.3.x | 12.0.0 |
| **Tomcat 10+** | Jakarta EE 10 | `jakarta.*` | 4.x | 13.0.0 (jakarta) |

### 3. Common Package Mapping (Tomcat 9 / Java EE 8)
| Component | Package |
|-----------|---------|
| Servlet | `javax.servlet.*` |
| JSF | `javax.faces.*` |
| CDI | `javax.enterprise.*`, `javax.inject.*` |
| Annotations | `javax.annotation.*` |
| EL | `javax.el.*` |
| JSP | `javax.servlet.jsp.*` |

### 4. Common XML Namespace Mapping
| File | Tomcat 9 (Java EE 8) | Tomcat 10+ (Jakarta EE 10) |
|------|---------------------|---------------------------|
| `web.xml` | `http://xmlns.jcp.org/xml/ns/javaee` v4.0 | `https://jakarta.ee/xml/ns/jakartaee` v6.0 |
| `beans.xml` | `http://xmlns.jcp.org/xml/ns/javaee` v2.0 | `https://jakarta.ee/xml/ns/jakartaee` v4.0 |
| `faces-config.xml` | `http://xmlns.jcp.org/xml/ns/javaee` v2.3 | `https://jakarta.ee/xml/ns/jakartaee` v4.0 |
| `persistence.xml` | `http://xmlns.jcp.org/xml/ns/persistence` v2.2 | `https://jakarta.ee/xml/ns/persistence` v3.0 |

### 5. Common Mistakes That Break Projects
```
❌ Having BOTH index.html AND index.xhtml (index.html wins)
❌ Using jakarta.faces in web.xml on Tomcat 9
❌ PrimeFaces 13 without <classifier>jakarta</classifier> on Jakarta EE 10
❌ Using javax.* imports on Tomcat 10+
❌ Using jakarta.* imports on Tomcat 9
❌ Missing beans.xml in WEB-INF/
❌ Not mapping FacesServlet in web.xml
❌ JSF 4.x on Tomcat 9 (incompatible)
```

---

## 🔧 JSF Lifecycle (6 Phases)

When you submit a form, all 6 phases execute in order:

```
1. RESTORE_VIEW        → Rebuild component tree from previous request
2. APPLY_REQUEST_VALUES → Extract submitted values from request
3. PROCESS_VALIDATIONS  → Validate all input values
4. UPDATE_MODEL_VALUES  → Update bean properties with valid values
5. INVOKE_APPLICATION   → Execute action methods (e.g., formBean.submit())
6. RENDER_RESPONSE      → Render the view back to the client
```

**Note:** On initial page load (GET request), only phases 1 and 6 execute. All 6 phases execute on form submit (POST request).

The `LifecyclePhaseListener` in this project logs all phases to the console:
```bash
tail -f /path/to/tomcat/logs/catalina.out | grep "Phase:"
```

---

## 📋 Troubleshooting

### 404 - Page Not Found
```
✅ Check: Tomcat is running
✅ Check: WAR is in webapps/ and extracted
✅ Check: No index.html blocking index.xhtml
✅ Check: FacesServlet mapped to *.xhtml in web.xml
```

### ClassNotFoundException: javax.faces...
```
✅ Check: JSF dependency in pom.xml
✅ Check: jakarta.faces-2.3.21.jar in WEB-INF/lib/
✅ Check: Maven built successfully
```

### PrimeFaces Components Not Rendering
```
✅ Check: primefaces-12.0.0.jar in WEB-INF/lib/
✅ Check: xmlns:p="http://primefaces.org/ui" in XHTML
✅ Check: No version mismatch with JSF
```

### CDI Injection Not Working
```
✅ Check: beans.xml exists in WEB-INF/
✅ Check: bean-discovery-mode="all"
✅ Check: Weld dependency in pom.xml
✅ Check: @Named + @RequestScoped on bean
```

### ClassFormatError or LinkageError
```
✅ Check: NO mixing of javax.* and jakarta.* imports
✅ Check: All versions are compatible for Tomcat 9
✅ Check: Clean target/ and rebuild: mvn clean package
```

---

## 🚀 Deployment Checklist

- [ ] Java 8+ installed
- [ ] Maven installed
- [ ] Tomcat 9.x installed
- [ ] `mvn clean package` succeeds
- [ ] WAR copied to `tomcat/webapps/`
- [ ] Tomcat started (`bin/startup.sh`)
- [ ] `http://localhost:8080/jsf-mastery/index.xhtml` loads
- [ ] Form submit works and shows lifecycle phases in logs
- [ ] PrimeFaces components render correctly

---

## 📚 Tech Stack Summary

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 1.8+ | Language |
| Maven | 3.6+ | Build tool |
| Tomcat | 9.x | Servlet container |
| Servlet API | 4.0.1 | HTTP handling |
| JSF (Mojarra) | 2.3.21 | Web framework |
| PrimeFaces | 12.0.0 | UI components |
| CDI (Weld) | 3.1.9.Final | Dependency injection |
| JSTL | 1.2 | Tag libraries |

---

## 🔗 Useful Links

- [JSF 2.3 Specification](https://jcp.org/en/jsr/detail?id=372)
- [PrimeFaces Documentation](https://primefaces.github.io/primefaces/12_0_0/)
- [Tomcat 9 Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)
- [Java EE 8 Tutorial](https://javaee.github.io/tutorial/)

---

**Built for Tomcat 9 with Java EE 8, JSF 2.3, and PrimeFaces 12**
