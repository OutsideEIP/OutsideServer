FROM openjdk:17

RUN mkdir /app
COPY . /app
WORKDIR /app

ARG OUTSIDE_SERVER_IGNORE_DATABASE_ERRORS
ENV OUTSIDE_SERVER_IGNORE_DATABASE_ERRORS $OUTSIDE_SERVER_IGNORE_DATABASE_ERRORS

RUN microdnf install findutils wget
RUN /app/gradlew build

CMD [ "/app/gradlew", "bootRun" ]