#!/bin/bash

mvn package

mvn install:install-file -Dfile=/Users/unadkarni/workspace/cc-demo-application/cc-demo-common/target/cc-demo-common-0.0.1-SNAPSHOT.jar -DgroupId=io.pivotal.cc.common -DartifactId=cc-demo-common -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -DgeneratePom=true
