#!/bin/bash

BLUE='\033[0;34m'

echo -e "\n${BLUE}------------- CREATE LOG FILES -------------${WHITE}\n"


touch traefik_log.log
touch traefik_access_log.log


echo -e "\n${BLUE}------------- LOG FILES CREATED -------------${WHITE}\n"