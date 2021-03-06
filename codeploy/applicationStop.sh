#!/bin/bash
# sudo systemctl stop tomcat9
pkill -f 'java -jar'
sudo rm -rf webappone/
sudo rm -rf codedeploy/
sudo rm -f appspec.yml