#!/usr/bin/env bash

# make sure the methodTrace.jfr file is created before running this script

cd src/main/java/org/jugistanbul/profile

jfr print --events jdk.MethodTrace methodTrace.jfr
