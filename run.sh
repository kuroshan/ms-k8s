#!/bin/bash

declare dc_infra=docker-compose.yml

function build_api() {
    cd bookmarker-api
    ./gradlew clean bootJar -x test
    cd ..
}

function start() {
    build_api
    echo "Starting all docker containers...."
    docker-compose -f ${dc_infra} up --build -d
    docker-compose -f ${dc_infra} logs -f
}

function stop() {
    echo "Stopping all docker containers...."
    docker-compose -f ${dc_infra} stop
    docker-compose -f ${dc_infra} rm -f
}

function restart() {
    stop
    sleep 3
    start
}

action="start"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}