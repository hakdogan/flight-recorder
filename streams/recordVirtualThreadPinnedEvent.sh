#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/stream/internal

java -XX:StartFlightRecording:filename=virtualThreadPinnedEvent.jfr \
  --enable-preview --source 20 VirtualThreadPinnedEvent.java
