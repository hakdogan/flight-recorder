# Record

Through a messaging example, this module shows you how to create your own events, record data, and view these records on the terminal.

```java
    @Name("org.jugistanbul.Hello")
    @Label("Hello Event")
    @Description("This example demonstrates the monitoring of events consisting of messages sent at a random time.")
    @Category({ "Demonstration", "Tutorial" })
    static class Hello extends Event {
        @Label("Message")
        String message;
    }

    private static void fireEvent(final int number){
        HELLO_EVENT.begin();
        var sleep = ThreadLocalRandom.current().nextInt(2, 10);
        try {
            TimeUnit.SECONDS.sleep(sleep);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        HELLO_EVENT.message = "Event " + number;
        HELLO_EVENT.commit();
    }
```
## To start recording
```bash
# sh recordEvent.sh

java -XX:StartFlightRecording:filename=helloEvent.jfr HelloEvent.java

[0.766s][info][jfr,startup] Started recording 1. No limit specified, using maxsize=250MB as default.
[0.766s][info][jfr,startup] 
[0.766s][info][jfr,startup] Use jcmd 12970 JFR.dump name=1 to copy recording data to file.
```

## To view and parse the recordings
```bash
# make sure the helloEvent.jfr file is created before running this script
# sh viewRecordFile.sh

jfr print --events Hello helloEvent.jfr

org.jugistanbul.Hello {
  startTime = 00:01:59.954
  duration = 9.01 s
  message = "Event 1"
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    HelloEvent.fireEvent(int) line: 39
    java.util.stream.Streams$RangeIntSpliterator.forEachRemaining(IntConsumer) line: 104
    java.util.stream.IntPipeline$Head.forEach(IntConsumer) line: 617
    HelloEvent.main(String[]) line: 27
    jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Method, Object, Object[])
  ]
}
...
```
