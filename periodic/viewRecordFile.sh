#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/periodic

# make sure the periodic.jfr file is created before running this script
jfr print --events ChangeDetector periodic.jfr
