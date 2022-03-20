#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/periodic

java -XX:StartFlightRecording:filename=periodic.jfr PeriodicEvent.java
