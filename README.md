## **Pre-requisites**

1. Java 11+

### **Lombok**

Since Lombok is used, you need to install the appropriate plugin within your IDE (https://projectlombok.org)

### **Code Style**

For those using Intellij IDEA there is already a formatter included in the Project. If it's not enabled by default you can do that in
File->Settings->Editor->Code Style-Scheme->Project. Also make sure that "Enable EditorConfig support" is checked, this can be found in
File->Settings->Editor->Code Style

## **Setup**

### **H2 In-memory DB**

Run project with: ```./mvnw spring-boot:run -pl application```

### **MySQL DB**

Run project with: ```./mvnw spring-boot:run -pl application -D"spring-boot.run.profiles"=prod```
