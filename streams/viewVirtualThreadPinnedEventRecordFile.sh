#!/usr/bin/env bash

cd src/main/java/org/jugistanbul/stream/internal

# make sure the virtualThreadPinnedEvent.jfr file is created before running this script
jfr print --events jdk.VirtualThreadPinned virtualThreadPinnedEvent.jfr
