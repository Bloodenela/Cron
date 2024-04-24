FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package

FROM openjdk:17-alpine

COPY --from=build /app/target/FailsafeCron-0.0.1-SNAPSHOT.jar ./root
COPY script.sh /root/script.sh

RUN chmod +x /root/script.sh
RUN apk add sudo
RUN apk add --update apk-cron && rm -rf /var/cache/apk/*
RUN apk add busybox-initscripts
RUN echo "*/1 * * * * /root/run.sh" > /etc/crontabs/root
RUN echo "Hello, world!"

CMD ["crond", "-f"]

