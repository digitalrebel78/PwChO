FROM java:8
LABEL maintainer="Roman Voitovych"
COPY Main.java .
RUN curl -L -o /mysql-connector-java-8.0.22.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.22/mysql-connector-java-8.0.22.jar
RUN javac Main.java
CMD java Main
CMD ["java", "-classpath", "mysql-connector-java-8.0.22.jar:.", "Main"]
