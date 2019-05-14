FROM java:8
WORKDIR /
ADD takenoko.jar takenoko.jar
EXPOSE 8080
CMD java - jar takenoko.jar