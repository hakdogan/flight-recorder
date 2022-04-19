#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/stream/active

java -XX:StartFlightRecording:filename=activeEvent.jfr GarbageCollectionInfoEvent.java
