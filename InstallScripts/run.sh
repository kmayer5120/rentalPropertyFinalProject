#!/bin/bash
# Here is the bash script that Chu laid out in class.
# May be helpful to change it a bit so it can run the
# server and client.
# We could just add two lines:
    # java Server&
    # java Client
    # (if our class names were Client and Server)

CLASSPATH=$DERBY_HOME/lib/derby.jar:$(pwd)
export CLASSPATH
# run java command. Just like String[] args in java
java $1 
# run second java command. Basically args[1]
# java $2 

