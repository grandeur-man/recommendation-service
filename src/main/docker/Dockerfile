FROM openjdk:8

MAINTAINER Samuel Idaewor <samuelidaewor@gmail.com>

EXPOSE 8080

#Install Recommendation Service artifact
VOLUME /tmp

ADD /maven/recommendation-service-1.0-SNAPSHOT.jar /recommendation-service.jar

RUN sh -c 'touch /recommendation-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/recommendation-service.jar"]
