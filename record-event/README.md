# Record Event

```bash
java -XX:StartFlightRecording:filename=helloEvent.jfr HelloEvent.java

[0.766s][info][jfr,startup] Started recording 1. No limit specified, using maxsize=250MB as default.
[0.766s][info][jfr,startup] 
[0.766s][info][jfr,startup] Use jcmd 12970 JFR.dump name=1 to copy recording data to file.
```

```bash
jfr print --events Hello helloEvent.jfr

org.jugistanbul.Hello {
  startTime = 00:01:59.954
  duration = 9.01 s
  message = "Event 1"
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    org.jugistanbul.record.event.HelloEvent.fireEvent(int) line: 39
    java.util.stream.Streams$RangeIntSpliterator.forEachRemaining(IntConsumer) line: 104
    java.util.stream.IntPipeline$Head.forEach(IntConsumer) line: 617
    org.jugistanbul.record.event.HelloEvent.main(String[]) line: 27
    jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Method, Object, Object[])
  ]
}
...
```
