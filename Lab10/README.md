# 📝 Tasks

1. Backend Quartz Job – Top 3 Cheapest Restaurants
	- Scheduled job fetches the 3 cheapest restaurants from the database.
	- Logs these restaurants and saves them into the database.
	- Handles lazy loading issues to avoid exceptions.
	- Job starts 20 seconds after application startup and runs only once.

2. Frontend – Display Top 3 Cheapest Restaurants
	- Added a component that displays the top 3 cheapest restaurants.
	- Sends GET request to backend to retrieve restaurant data.
	- Displays restaurant details with proper formatting.
	- Supports i18n translations for all labels.
	- Styled with SCSS for better UI.


## 🛠️ Environment Note

Before starting the Spring project, make sure to download [Apache Maven 4.0.0 RC2](https://dlcdn.apache.org/maven/maven-4/4.0.0-rc-2/binaries/).  
After downloading, extract the archive and run the following command to build the project and allow MapStruct to generate necessary code:

```bash
/path/to/apache-maven-4.0.0-rc-2/bin/mvn clean package
````

📌 Replace /path/to/ with the actual path where you extracted Maven.
