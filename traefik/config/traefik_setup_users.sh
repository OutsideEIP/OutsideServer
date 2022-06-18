#!/bin/bash

BLUE='\033[0;34m'

if [ $# -ne "2" ]
    then
        echo -e "No enought args:\n$ ./treafik_setup_users.sh TRAEFIK_DASHBOARD_USER TRAEFIK_DASHBOARD_PASSWORD"
        exit 1
fi

TRAEFIK_DASHBOARD_USER=$1
TRAEFIK_DASHBOARD_PASSWORD=$2

echo -e "\n${BLUE}------------- CREATE TRAEFIK USERS -------------${WHITE}\n"

htpasswd -nb $TRAEFIK_DASHBOARD_USER $TRAEFIK_DASHBOARD_PASSWORD > usersFile

echo -e "\n${BLUE}------------- TRAEFIK USERS CREATED -------------${WHITE}\n"