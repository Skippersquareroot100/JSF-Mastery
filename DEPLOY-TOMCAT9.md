# 🚀 Deploy JSF-Mastery on Tomcat 9.0.116

## ⚠️ Important: Tomcat 9 uses Java EE 8 (javax.*) not Jakarta EE 10 (jakarta.*)

The project has been downgraded from Jakarta EE 10 → Java EE 8 for Tomcat 9 compatibility.

---

## Step 1: Build the WAR file

```bash
cd /Users/marufhasananik/Downloads/JSF-Mastery
mvn clean package
```

You should see:
```
[INFO] BUILD SUCCESS
[INFO] Building war: /Users/marufhasananik/Downloads/JSF-Mastery/target/JSF-Lifecycle-Lab-1.0-SNAPSHOT.war
```

---

## Step 2: Copy WAR to Tomcat webapps

Find where your Tomcat 9.0.116 is installed, then:

```bash
# Example path - adjust to your actual Tomcat location
cp target/JSF-Lifecycle-Lab-1.0-SNAPSHOT.war /path/to/tomcat/apache-tomcat-9.0.116/webapps/

# Or rename it for easier access
cp target/JSF-Lifecycle-Lab-1.0-SNAPSHOT.war /path/to/tomcat/apache-tomcat-9.0.116/webapps/jsf-mastery.war
```

---

## Step 3: Start Tomcat

```bash
cd /path/to/tomcat/apache-tomcat-9.0.116/bin
chmod +x *.sh   # Only needed first time on Mac/Linux
./startup.sh
```

Tomcat will automatically:
1. Extract the WAR file
2. Deploy the application

---

## Step 4: Access the Application

Open your browser and go to:

**http://localhost:8080/jsf-mastery/index.xhtml**

Or if you didn't rename the WAR:
**http://localhost:8080/JSF-Lifecycle-Lab-1.0-SNAPSHOT/index.xhtml**

---

## 🔧 Troubleshooting

### If you get 404 on index.xhtml:
1. Check Tomcat logs: `tail -f /path/to/tomcat/logs/catalina.out`
2. Make sure the WAR was extracted in `webapps/`
3. Restart Tomcat: `./shutdown.sh` then `./startup.sh`

### If you see ClassNotFoundException for CDI:
The project includes `weld-servlet-core` for CDI support. If issues persist:
1. Verify `WEB-INF/lib/` contains the Weld JARs
2. Check `WEB-INF/beans.xml` exists with `bean-discovery-mode="all"`

### If PrimeFaces components don't render:
1. Verify `primefaces-12.0.0.jar` is in `WEB-INF/lib/`
2. Check that `index.xhtml` uses: `xmlns:p="http://primefaces.org/ui"`

---

## 📋 What Changed for Tomcat 9

| Component | Jakarta EE 10 | Java EE 8 (Tomcat 9) |
|-----------|---------------|---------------------|
| Packages | `jakarta.*` | `javax.*` |
| Java Version | 17 | 1.8 |
| JSF | 4.0.1 | 2.3.21 |
| PrimeFaces | 13.0.0 (jakarta) | 12.0.0 |
| CDI Weld | 5.1.2 | 3.1.9 |
| Servlet API | 6.0 | 4.0.1 |
| web.xml version | 6.0 | 4.0 |

---

## ✅ Verify Deployment

1. Go to: **http://localhost:8080/manager/html** (default: admin/admin or tomcat/tomcat)
2. Look for `JSF-Lifecycle-Lab` or `jsf-mastery` in the applications list
3. Status should show "Running"
