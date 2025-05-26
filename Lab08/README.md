# 📝 Tasks

1. **Display User Information from Access Token**
   - Implemented a frontend component that retrieves and displays basic information about the currently logged-in user (e.g., username, role).
   - Decoded the access token using the `jwt-decode` library to extract user details.
   - Ensured that the component is only accessible to authenticated users.
   - Displayed user information in a protected section of the application.

2. **Automatic Logout on Token Expiry**
   - Implemented logic to automatically log out the user when the refresh token expires or when acquiring a new access token fails.
   - Redirected the user to the login screen upon token expiration.
   - Displayed a user-friendly message notifying the user about the session timeout.

## 🛠️ Environment Note

Before starting the Spring project, make sure to download [Apache Maven 4.0.0 RC2](https://dlcdn.apache.org/maven/maven-4/4.0.0-rc-2/binaries/).  
After downloading, extract the archive and run the following command to build the project and allow MapStruct to generate necessary code:

```bash
/path/to/apache-maven-4.0.0-rc-2/bin/mvn clean package
````

📌 Replace /path/to/ with the actual path where you extracted Maven.
