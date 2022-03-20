#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/record

#make sure the helloEvent.jfr file is created before running this script
jfr print --events Hello helloEvent.jfr
