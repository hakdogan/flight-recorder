#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/category

# jfr print --events FileList categorizedEvents.jfr
# jfr print --events FileRead categorizedEvents.jfr

# make sure the categorizedEvents.jfr file is created before running this script
jfr print --categories JUG_Istanbul categorizedEvents.jfr
