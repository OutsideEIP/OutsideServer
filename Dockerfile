FROM openjdk:17

RUN mkdir /app
COPY . /app
WORKDIR /app

RUN microdnf install findutils
RUN /app/gradlew build

CMD [ "/app/gradlew", "bootRun" ]