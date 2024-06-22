#!/bin/bash

# Determine the directory of the script
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

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

    echo "Waiting for containers to start..."
    sleep 30

    echo "Containers are up and running:"
    docker ps
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