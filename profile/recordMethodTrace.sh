#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/profile

java -XX:StartFlightRecording:jdk.MethodTrace#filter=org.jugistanbul.profile.RemoteProcedure::call,filename=methodTrace.jfr Caller.java