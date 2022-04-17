package org.jugistanbul.stream;

import jdk.jfr.consumer.EventStream;
import jdk.jfr.consumer.RecordingStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GarbageCollectionInfoEvent
{
    public static void main(String[] args) throws IOException {

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
    }
}
