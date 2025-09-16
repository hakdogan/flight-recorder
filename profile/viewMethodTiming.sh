#!/usr/bin/env bash

# make sure the methodTiming.jfr file is created before running this script

cd src/main/java/org/jugistanbul/profile

jfr view method-timing methodTiming.jfr
