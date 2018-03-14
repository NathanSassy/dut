#!/bin/bash

CURL_OPTIONS='--write-out "%{http_code}\n" --silent --output /dev/null'
ROUTE="http://localhost:3000/api/tasks"
check_status () {
    if [[ $1 = *"200"* ]]; then
        echo "[OK] $2 succeed"
    else
        echo "[FAILED] $2 failed"
    fi
}

# Get all tasks
STATUS=$(curl ${CURL_OPTIONS} -X GET ${ROUTE})
check_status "${STATUS}" "Get all task request"

# Post a new task
OUTPUT=$(curl --write-out "%{http_code}\n" --silent -X POST ${ROUTE} \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -d 'nom=test%20ag&description=blabla&contexte=test&duree=1000')
STATUS=$(echo ${OUTPUT} | cut -d} -f2)
if [[ ${STATUS} = *"200"* ]]; then
    ID=$(echo $OUTPUT | cut -d\" -f4)
else
    ID="-1"
fi
check_status "${STATUS}" "Post a new task request"
echo "ID is ${ID}"

# Get only the new task
STATUS=$(curl ${CURL_OPTIONS} -X GET \
    ${ROUTE}/${ID})
check_status "${STATUS}" "Get task with id request"

# Update the task
STATUS=$(curl ${CURL_OPTIONS} -X PUT \
    ${ROUTE}/${ID} \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -d 'nom=test%20ag&description=blabla%20mod&contexte=test&duree=1000')
check_status "${STATUS}" "Update a task request"

# Delete the task
STATUS=$(curl ${CURL_OPTIONS} -X DELETE \
    ${ROUTE}/${ID})
check_status "${STATUS}" "Delete a task request"
