#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/stream

java -XX:StartFlightRecording:filename=activeEvent.jfr GarbageCollectionInfoEvent.java
