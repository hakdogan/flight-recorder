# Improvements to profiling in Java 25

[JEP 520](https://openjdk.org/jeps/520) proposes extending the JDK Flight Recorder (JFR) with facilities for method timing and tracing via bytecode instrumentation.

Two new events, `jdk.MethodTiming` and `jdk.MethodTrace`, are introduced for this purpose. 

## To start recording
```bash
# for method timing
# sh recordMethodTiming.sh

java '-XX:StartFlightRecording:method-timing=org.jugistanbul.profile.RemoteProcedure::call,filename=methodTiming.jfr' Caller.java

[0.228s][info][jfr,startup] Started recording 1. No limit specified, using maxsize=250MB as default.
[0.228s][info][jfr,startup] 
[0.228s][info][jfr,startup] Use jcmd 22786 JFR.dump name=1 to copy recording data to file.
Calling remote procedure
Calling remote procedure
Calling remote procedure
Calling remote procedure
Calling remote procedure

# for method trace
# sh recordMethodTrace.sh

java -XX:StartFlightRecording:jdk.MethodTrace#filter=org.jugistanbul.profile.RemoteProcedure::call,filename=methodTrace.jfr Caller.java

[0.203s][info][jfr,startup] Started recording 1. No limit specified, using maxsize=250MB as default.
[0.203s][info][jfr,startup] 
[0.203s][info][jfr,startup] Use jcmd 22947 JFR.dump name=1 to copy recording data to file.
Calling remote procedure
Calling remote procedure
Calling remote procedure
Calling remote procedure
Calling remote procedure
```

## To view and parse the recordings

```bash
# for method timing
# sh viewMethodTiming.sh

                                            Method Timing

Timed Method                                   Invocations  Minimum Time  Average Time  Maximum Time
---------------------------------------------- ----------- ------------- ------------- -------------
org.jugistanbul.profile.RemoteProcedure.call()           5 313.000000 ms 379.000000 ms 483.000000 ms


# for method trace
# sh viewMethodTrace.sh

jdk.MethodTrace {
    startTime = 23:31:18.832 (2025-09-16)
    duration = 338 ms
    method = org.jugistanbul.profile.RemoteProcedure.call()
    eventThread = "main" (javaThreadId = 3)
    stackTrace = [
        org.jugistanbul.profile.Caller.lambda$main$0(int) line: 19
        java.util.stream.Streams$RangeIntSpliterator.forEachRemaining(IntConsumer) line: 104
        java.util.stream.IntPipeline$Head.forEach(IntConsumer) line: 619
        org.jugistanbul.profile.Caller.main() line: 15
        jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Object, Object[]) line: 104
    ]
}

jdk.MethodTrace {
    startTime = 23:31:19.483 (2025-09-16)
    duration = 253 ms
    method = org.jugistanbul.profile.RemoteProcedure.call()
    eventThread = "main" (javaThreadId = 3)
    stackTrace = [
        org.jugistanbul.profile.Caller.lambda$main$0(int) line: 19
        java.util.stream.Streams$RangeIntSpliterator.forEachRemaining(IntConsumer) line: 104
        java.util.stream.IntPipeline$Head.forEach(IntConsumer) line: 619
        org.jugistanbul.profile.Caller.main() line: 15
        jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Object, Object[]) line: 104
    ]
}
...
```