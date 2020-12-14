FROM java:8
LABEL maintainer="Roman Voitovych"
COPY Main.java .
RUN curl -L -o /mysql-connector-java-8.0.13.jar https://repol.maven.org/maven2/mysql/mysql-connector-java/8.0.13/mysql-connector-java-8.0.13.jar
RUN javac Main.java
CMD java Main
CMD ["java", "-classpath", "mysql-connector-java-8.0.13.jar:.", "Main"]
