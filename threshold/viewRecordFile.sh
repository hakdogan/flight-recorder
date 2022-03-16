#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/threshold

#make sure the eventThreshold.jfr file is created before running this script
jfr print --events LongExecutionTime eventThreshold.jfr
