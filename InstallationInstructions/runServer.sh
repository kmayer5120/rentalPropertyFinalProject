#!/bin/bash
# Be sure to make this script executable first by running the command:
    # chmod 774 run.sh
# Then issue the following command to run the script:
    # ./runServer.sh

cd ..
cd RentalPropertyManagementSystem/src
java -classpath ".:sqlite-jdbc-3.21.0.jar" Server 

