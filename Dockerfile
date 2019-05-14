FROM java:8
WORKDIR /
ADD takenoko.Main.jar takenoko.Main.jar
EXPOSE 8080
CMD java - jar takenoko.Main.jar