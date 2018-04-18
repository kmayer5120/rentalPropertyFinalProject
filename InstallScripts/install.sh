#!/bin/bash

echo "Installing Rental Property Management System"

echo "Creating folder for application"
mkdir RentalPropertyManagementSystem
cd RentalPropertyManagementSystem

echo "Exporting classpath"
CLASSPATH=$DERBY_HOME/lib/derby.jar:$(pwd)
export CLASSPATH

echo "Retrieving source code from git repository"
git clone https://github.com/kmayer5120/rentalPropertyFinalProject.git

echo "Installation complete!"
echo "See read me file for more information."
