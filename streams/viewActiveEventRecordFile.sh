#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/stream

# make sure the activeEvent.jfr file is created before running this script
jfr print --events jdk.GarbageCollection activeEvent.jfr
