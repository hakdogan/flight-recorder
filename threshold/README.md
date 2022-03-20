# Threshold

@Threshold annotation lets you configure event thresholds. This module shows that when you define a threshold, the Flight Recorder does not record events if its duration is less than the defined threshold.

```java
    @Name("org.jugistanbul.LongExecutionTime")
    @Label("Long Execution Time")
    @Threshold("50 ms")
    static class LongExecutionTime extends Event {
        @Label("Event name")
        String eventName;
    }
```

## To start recording
```bash
# sh recordEvent.sh

java -XX:StartFlightRecording:filename=eventThreshold.jfr EventThreshold.java

[0.772s][info][jfr,startup] Started recording 1. No limit specified, using maxsize=250MB as default.
[0.772s][info][jfr,startup] 
[0.772s][info][jfr,startup] Use jcmd 22881 JFR.dump name=1 to copy recording data to file.
```

## To view and parse the recordings
```bash
# sh viewRecordFile.sh

# make sure the eventThreshold.jfr file is created before running this script
jfr print --events LongExecutionTime eventThreshold.jfr

org.jugistanbul.LongExecutionTime {
  startTime = 09:43:16.123
  duration = 50.1 ms
  eventName = "yqwywtgunb API"
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    org.jugistanbul.threshold.EventThreshold.remoteApiCall(String) line: 40
    org.jugistanbul.threshold.EventThreshold.lambda$main$0(int) line: 28
    java.util.stream.Streams$RangeIntSpliterator.forEachRemaining(IntConsumer) line: 104
    java.util.stream.IntPipeline$Head.forEach(IntConsumer) line: 617
    org.jugistanbul.threshold.EventThreshold.main(String[]) line: 28
  ]
}

org.jugistanbul.LongExecutionTime {
  startTime = 09:43:16.174
  duration = 57.8 ms
  eventName = "akbsteirgd API"
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    org.jugistanbul.threshold.EventThreshold.remoteApiCall(String) line: 40
    org.jugistanbul.threshold.EventThreshold.lambda$main$0(int) line: 28
    java.util.stream.Streams$RangeIntSpliterator.forEachRemaining(IntConsumer) line: 104
    java.util.stream.IntPipeline$Head.forEach(IntConsumer) line: 617
    org.jugistanbul.threshold.EventThreshold.main(String[]) line: 28
  ]
}

org.jugistanbul.LongExecutionTime {
  startTime = 09:43:16.279
  duration = 57.0 ms
  eventName = "ibxwvasgvt API"
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    org.jugistanbul.threshold.EventThreshold.remoteApiCall(String) line: 40
    org.jugistanbul.threshold.EventThreshold.lambda$main$0(int) line: 28
    java.util.stream.Streams$RangeIntSpliterator.forEachRemaining(IntConsumer) line: 104
    java.util.stream.IntPipeline$Head.forEach(IntConsumer) line: 617
    org.jugistanbul.threshold.EventThreshold.main(String[]) line: 28
  ]
}

org.jugistanbul.LongExecutionTime {
  startTime = 09:43:16.426
  duration = 52.4 ms
  eventName = "ifkedgzouv API"
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    org.jugistanbul.threshold.EventThreshold.remoteApiCall(String) line: 40
    org.jugistanbul.threshold.EventThreshold.lambda$main$0(int) line: 28
    java.util.stream.Streams$RangeIntSpliterator.forEachRemaining(IntConsumer) line: 104
    java.util.stream.IntPipeline$Head.forEach(IntConsumer) line: 617
    org.jugistanbul.threshold.EventThreshold.main(String[]) line: 28
  ]
}

org.jugistanbul.LongExecutionTime {
  startTime = 09:43:16.525
  duration = 54.2 ms
  eventName = "xemnkgoqdx API"
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    org.jugistanbul.threshold.EventThreshold.remoteApiCall(String) line: 40
    org.jugistanbul.threshold.EventThreshold.lambda$main$0(int) line: 28
    java.util.stream.Streams$RangeIntSpliterator.forEachRemaining(IntConsumer) line: 104
    java.util.stream.IntPipeline$Head.forEach(IntConsumer) line: 617
    org.jugistanbul.threshold.EventThreshold.main(String[]) line: 28
  ]
}
```
