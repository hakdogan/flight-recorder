# Periodic

FlightRecorder API allows you to create a periodic event with @Period annotation. This module exemplifies the use of a periodic event that runs every second to keep track of file changes.

```java
    @Name("org.jugistanbul.ChangeDetector")
    @Label("Change Detector")
    @Period("1 s")
    static class DirectoryWatcher extends Event {
        @Label("Change Detect")
        boolean change;
    }

    ...
    
    Runnable hook = createHook();
    FlightRecorder.addPeriodicEvent(DirectoryWatcher.class, hook);

    Scanner scanner = new Scanner(System.in);
    System.out.println("You can terminate the periodic event by pressing any key...");
    scanner.nextLine();

    FlightRecorder.removePeriodicEvent(hook);
```

## To start recording
```bash
# sh recordEvent.sh

[0.598s][info][jfr,startup] Started recording 1. No limit specified, using maxsize=250MB as default.
[0.598s][info][jfr,startup] 
[0.598s][info][jfr,startup] Use jcmd 10341 JFR.dump name=1 to copy recording data to file.
You can terminate the periodic event by pressing any key...
```

## To view and parse the recordings

```bash
# sh viewRecordFile.sh

# make sure the periodic.jfr file is created before running this script
jfr print --events ChangeDetector periodic.jfr

org.jugistanbul.ChangeDetector {
  startTime = 22:03:30.392
  change = false
  eventThread = "JFR Periodic Tasks" (javaThreadId = 13)
  stackTrace = [
    org.jugistanbul.periodic.PeriodicEvent.lambda$createHook$0() line: 53
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 90
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 86
    java.security.AccessController.doPrivileged(PrivilegedAction, AccessControlContext) line: 399
    jdk.jfr.internal.RequestEngine$RequestHook.executeSecure() line: 86
  ]
}

org.jugistanbul.ChangeDetector {
  startTime = 22:03:31.401
  change = false
  eventThread = "JFR Periodic Tasks" (javaThreadId = 13)
  stackTrace = [
    org.jugistanbul.periodic.PeriodicEvent.lambda$createHook$0() line: 53
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 90
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 86
    java.security.AccessController.doPrivileged(PrivilegedAction, AccessControlContext) line: 399
    jdk.jfr.internal.RequestEngine$RequestHook.executeSecure() line: 86
  ]
}

org.jugistanbul.ChangeDetector {
  startTime = 22:03:32.400
  change = true
  eventThread = "JFR Periodic Tasks" (javaThreadId = 13)
  stackTrace = [
    org.jugistanbul.periodic.PeriodicEvent.lambda$createHook$0() line: 53
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 90
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 86
    java.security.AccessController.doPrivileged(PrivilegedAction, AccessControlContext) line: 399
    jdk.jfr.internal.RequestEngine$RequestHook.executeSecure() line: 86
  ]
}

org.jugistanbul.ChangeDetector {
  startTime = 22:03:33.406
  change = false
  eventThread = "JFR Periodic Tasks" (javaThreadId = 13)
  stackTrace = [
    org.jugistanbul.periodic.PeriodicEvent.lambda$createHook$0() line: 53
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 90
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 86
    java.security.AccessController.doPrivileged(PrivilegedAction, AccessControlContext) line: 399
    jdk.jfr.internal.RequestEngine$RequestHook.executeSecure() line: 86
  ]
}

org.jugistanbul.ChangeDetector {
  startTime = 22:03:34.407
  change = true
  eventThread = "JFR Periodic Tasks" (javaThreadId = 13)
  stackTrace = [
    org.jugistanbul.periodic.PeriodicEvent.lambda$createHook$0() line: 53
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 90
    jdk.jfr.internal.RequestEngine$RequestHook$1.run() line: 86
    java.security.AccessController.doPrivileged(PrivilegedAction, AccessControlContext) line: 399
    jdk.jfr.internal.RequestEngine$RequestHook.executeSecure() line: 86
  ]
}
```
