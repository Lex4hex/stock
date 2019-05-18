FROM openjdk:11-jre-slim
RUN mkdir /opt/stock
VOLUME /opt/stock

ADD target/stocks-0.0.1-SNAPSHOT-exec.jar app.jar
ENTRYPOINT ["sh", "-c", "./app.jar"]
