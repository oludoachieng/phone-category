# phone-category
An app that fetches customer details (id, name, phone, validity and country code) and filters based on country and validity


Clone the project


Navigate to the project folder


TO RUN THE APP ON YOUR LOCAL MACHINE:

Java 11 needs to be installed

Maven 3.0+ needs to be installed

SQLite 3.0+ needs to be installed


run the following 1 command

1. mvn spring-boot:run

Nagivate to http://localhost:8080/customer on your browser.

TO RUN THE APP ON DOCKER:

Docker needs to be installed

run the following 3 commands

1. mvn clean install

2. docker build --tag=phone-category:latest .

3. docker run -p 8080:8080 phone-category

Nagivate to http://localhost:8080/customer on your browser.
