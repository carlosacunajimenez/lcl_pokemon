#!/bin/bash

# Determine the directory of the script
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

CONTAINER_NAME=postgres-db
LOCAL_DUMP_FILE_PATH=docker/dbDump/dumpfile.tar
CONTAINER_DUMP_FILE_PATH=/var/lib/dumpfile.tar
DB_USER=user
DB_NAME=mydb

# Function to clean and build the project
build_project() {
    echo "Building the project..."
    ./gradlew clean build
}

# Function to set up Docker containers
setup_containers() {
    echo "Stopping and removing any existing containers..."
    docker-compose -f docker/docker-compose.yml down

    echo "Building Docker images and starting containers..."
    docker-compose -f docker/docker-compose.yml up --build -d

    echo "Containers are up and running:"
    docker ps
}

load_dbDump() {
  echo "Copying dump file to the container..."
  docker cp $LOCAL_DUMP_FILE_PATH $CONTAINER_NAME:$CONTAINER_DUMP_FILE_PATH

  # Restore the dump file inside the container
  echo "Restoring the database dump..."
  docker exec -i $CONTAINER_NAME psql -U $DB_USER -d $DB_NAME -f $CONTAINER_DUMP_FILE_PATH
}

generate_dbDump(){
  echo "Copying db dump into local project"
  docker exec -t postgres-db pg_dump -U user mydb > dumpfile.tar
}


# Main script logic
case "$1" in
    setup)
        build_project
        setup_containers
        ;;
    build)
        build_project
        ;;
    loaddbdump)
        load_dbDump
        ;;
     generateDbDump)
       generate_dbDump
       ;;
    start)
        setup_containers
        ;;
    stop)
        echo "Stopping containers..."
        docker-compose -f docker/docker-compose.yml down
        ;;
    *)
        echo "Usage: $0 {setup|build|start|stop}"
        exit 1
        ;;
esac

exit 0