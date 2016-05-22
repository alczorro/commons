#!/bin/sh

mvn install:install-file -Dfile=localdep/SimpleAuth.jar \
    -DgroupId=net.duckling -DartifactId=simpleauth -Dversion=1.0 \
    -Dpackaging=jar
