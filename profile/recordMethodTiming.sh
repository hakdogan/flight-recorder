#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/profile

java '-XX:StartFlightRecording:method-timing=org.jugistanbul.profile.RemoteProcedure::call,filename=methodTiming.jfr' Caller.java