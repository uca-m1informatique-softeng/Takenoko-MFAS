FROM java:8
WORKDIR /
ADD Commun.jar Commun.jar
EXPOSE 8080
CMD java -jar Commun.jar