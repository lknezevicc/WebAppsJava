# 📝 Tasks

1. **Use only JPA for data access**
    - All database interactions must be implemented using standard Spring Data JPA repositories.

2. **Add `createdAt` Timestamp to Review Model**
    - Apply all required changes in the service layer, controller layer, and DTO/command classes to support this field.

3. **Display Top-Rated Restaurants on the Frontend**
    - Implement endpoints in the backend that return the restaurant with the highest average rating:
        - From the **last month**
        - From the **last week**
    - On the frontend (Angular), fetch this data and display the top-rated restaurants in a visually clear section.

## 🛠️ Environment Note

Before starting the Spring project, make sure to download [Apache Maven 4.0.0 RC2](https://dlcdn.apache.org/maven/maven-4/4.0.0-rc-2/binaries/).  
After downloading, extract the archive and run the following command to build the project and allow MapStruct to generate necessary code:

```bash
/path/to/apache-maven-4.0.0-rc-2/bin/mvn clean package
````

📌 Replace /path/to/ with the actual path where you extracted Maven.
