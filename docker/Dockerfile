# Dockerfile located at project-root/docker/Dockerfile

# Use the official Tomcat image from the Docker Hub
FROM tomcat:9.0-jdk11-openjdk

# Remove default webapps to clean up the Tomcat installation
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file to the webapps directory
COPY ../lcl_pokemon_rest/build/libs/app.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port that Tomcat runs on
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
