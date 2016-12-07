#!/bin/sh

if [ -z "$1" ]
then
  echo "Please provide the password when executing this utility"
  echo "Example: ./encrypt.sh MyPassword"
  exit
fi;

#Set the path to jennifer-view-adapter-jira.jar
ADAPTER_CLASS_PATH="../jennifer-view-adapter-jira.jar";

#Set view server home
VIEW_SERVER_HOME="/Users/khalid/jennifer/testing/server.view"

KRYPTO=adapter.jennifer.jira.util.Krypto

java -cp $ADAPTER_CLASS_PATH:$VIEW_SERVER_HOME/lib/commons-codec-1.6.jar $KRYPTO e $1
