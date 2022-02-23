FROM openjdk:11
ENV PHONE_CATEGORY_HOME=/usr/phone_category
WORKDIR $PHONE_CATEGORY_HOME
MAINTAINER https://github.com/oludoachieng
COPY target/phone-category-0.0.1-SNAPSHOT.jar phone_category.jar
COPY sample.db sample.db
EXPOSE 8080
ENTRYPOINT ["java","-jar","phone_category.jar"]
