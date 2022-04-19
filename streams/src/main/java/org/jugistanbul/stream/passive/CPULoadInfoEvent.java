package org.jugistanbul.stream.passive;

import jdk.jfr.consumer.EventStream;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class CPULoadInfoEvent
{
    static int CPU_LOAD_EVENTS = 5;

    public static void main(String[] args) throws IOException {

        AtomicInteger timer = new AtomicInteger();

        try (var es = EventStream.openRepository()) {
            es.onEvent("jdk.CPULoad", event -> {
                System.out.println("CPU Load " + event.getEndTime());
                System.out.println("Machine total: " + 100 * event.getFloat("machineTotal") + "%");
                System.out.println("JVM User: " + 100 * event.getFloat("jvmUser") + "%");
                System.out.println("JVM System: " + 100 * event.getFloat("jvmSystem") + "%");
                System.out.println();

                if (timer.incrementAndGet() == CPU_LOAD_EVENTS) {
                    System.exit(0);
                }
            });

            es.start();
        }
    }
}
