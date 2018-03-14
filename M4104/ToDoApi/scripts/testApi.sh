#!/bin/bash

CURL_OPTIONS='--write-out "%{http_code}\n" --silent --output /dev/null'
check_status () {
    if [[ $1 = *"200"* ]]; then
        echo "[OK] $2 succeed"
    else
        echo "[FAILED] $2 failed"
    fi
}

# Get all tasks
STATUS=$(curl ${CURL_OPTIONS} -X GET http://localhost:3000/tasks)
check_status "${STATUS}" "Get all task request"

# Post a new task
STATUS=$(curl ${CURL_OPTIONS} -X POST http://localhost:3000/tasks \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -d 'nom=test%20ag&description=blabla&contexte=test&duree=1000')
check_status "${STATUS}" "Post a new task request"

# Get only the new task
STATUS=$(curl ${CURL_OPTIONS} -X GET \
    http://localhost:3000/tasks/5aa8e8c17f075e4b2c2ae0dd)
check_status "${STATUS}" "Get task with id request"

# Update the task
STATUS=$(curl ${CURL_OPTIONS} -X PUT \
    http://localhost:3000/tasks/5aa8e8c17f075e4b2c2ae0dd \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -d 'nom=test%20ag&description=blabla%20mod&contexte=test&duree=1000')
check_status "${STATUS}" "Update a task request"

# Delete the task
STATUS=$(curl ${CURL_OPTIONS} -X DELETE \
    http://localhost:3000/tasks/5aa8e8c17f075e4b2c2ae0dd)
check_status "${STATUS}" "Delete a task request"
