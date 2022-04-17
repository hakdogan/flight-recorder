# Event Stream

FlightRecorder API allows you to subscribe to specific JFR data via event streaming. An event stream is a sequence of events. Streams that start with a recording are called active event streams, while streams created from the current JVM repository are called passive event streams. This module exemplifies the use of event streams to monitoring events.


```java
        try (var rs = new RecordingStream()) {
            rs.onEvent("jdk.GarbageCollection", event -> {
                System.out.println("Garbage collection: " + event.getLong("gcId"));
                System.out.println("Cause: " + event.getString("cause"));
                System.out.println("Total pause: " + event.getDuration("sumOfPauses"));
                System.out.println("Longest pause: " + event.getDuration("longestPause"));
                System.out.println();
            });
            rs.startAsync();

            for (int i = 0; i < 1_000_000; i++) {
                List<Integer> numberList = new ArrayList<>();
                for (int j = 0; j < 1_000_000; j++) {
                    numberList.add(j);
                }
            }
        }
```

## To start recording

```bash
# recordActiveEvent.sh

java -XX:StartFlightRecording:filename=activeEvent.jfr GarbageCollectionInfoEvent.java

[0.615s][info][jfr,startup] Started recording 1. No limit specified, using maxsize=250MB as default.
[0.615s][info][jfr,startup] 
[0.615s][info][jfr,startup] Use jcmd 17470 JFR.dump name=1 to copy recording data to file.
Garbage collection: 2
Cause: G1 Evacuation Pause
Total pause: PT0.037338952S
Longest pause: PT0.037338952S

Garbage collection: 3
Cause: G1 Evacuation Pause
Total pause: PT0.078268124S
Longest pause: PT0.078268124S

Garbage collection: 4
Cause: G1 Evacuation Pause
Total pause: PT0.020717297S
Longest pause: PT0.020717297S

Garbage collection: 6
Cause: G1 Evacuation Pause
Total pause: PT0.020502123S
Longest pause: PT0.020502123S
```

## To view and parse the recordings

```bash
# sh viewActiveEventRecordFile.sh

# make sure the activeEvent.jfr file is created before running this script
jfr print --events jdk.GarbageCollection activeEvent.jfr

jdk.GarbageCollection {
  startTime = 17:46:37.562 (2022-04-17)
  duration = 8.57 ms
  gcId = 1
  name = "G1New"
  cause = "G1 Evacuation Pause"
  sumOfPauses = 8.57 ms
  longestPause = 8.57 ms
}

jdk.GarbageCollection {
  startTime = 17:46:37.875 (2022-04-17)
  duration = 37.3 ms
  gcId = 2
  name = "G1New"
  cause = "G1 Evacuation Pause"
  sumOfPauses = 37.3 ms
  longestPause = 37.3 ms
}

jdk.GarbageCollection {
  startTime = 17:46:37.993 (2022-04-17)
  duration = 78.3 ms
  gcId = 3
  name = "G1New"
  cause = "G1 Evacuation Pause"
  sumOfPauses = 78.3 ms
  longestPause = 78.3 ms
}
```

![](../images/image2.png)
