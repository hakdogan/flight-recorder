#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/stream/passive

java -XX:StartFlightRecording:filename=passiveEvent.jfr CPULoadInfoEvent.java
