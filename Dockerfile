FROM openjdk:11
VOLUME /tmp
ADD ./target/microservice-bank-account-0.0.1-SNAPSHOT.jar microservice-bank-account.jar
ENTRYPOINT ["java", "-jar", "/microservice-bank-account.jar"]