#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/threshold

java -XX:StartFlightRecording:filename=eventThreshold.jfr EventThreshold.java
