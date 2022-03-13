#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/category

java -XX:StartFlightRecording:filename=categorizedEvents.jfr CategorizedEvents.java
