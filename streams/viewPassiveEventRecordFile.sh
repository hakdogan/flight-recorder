#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/stream/passive

# make sure the passiveEvent.jfr file is created before running this script
jfr print --events jdk.CPULoad passiveEvent.jfr
