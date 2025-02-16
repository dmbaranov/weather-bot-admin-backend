# Weather Bot admin BE

This is backend part of the admin panel to control [WeatherAdminBot](https://github.com/dmbaranov/CoopWeatherBot). Built with Kotlin and Spring Boot.


## Dependencies:
- PostgreSQL (the one that is used by the bot); 
- RabbitMQ;


## How to run:
- Run `./gradlew bootRun` to run the app in development mode.
  - Run `./gradlew compileKotlin --continuous --parallel --build-cache --configuration-cache` in a separate tab to compile project on the fly when saving changes;
  - Or simply open and run project through IntelliJ IDEA;
- Build Docker image to run app in production mode
  - `docker build -t weatherbot-admin-be .`
  - `docker run -d -p 9000:9000 --name weatherbot-admin-be weatherbot-admin-be`
