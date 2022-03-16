package org.jugistanbul.threshold;

import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Threshold;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author hakdogan (huseyin.akdogan@patikaglobal.com)
 * Created on 15.03.2022
 ***/
public class EventThreshold
{
    @Name("org.jugistanbul.LongExecutionTime")
    @Label("Long Execution Time")
    @Threshold("50 ms")
    static class LongExecutionTime extends Event {
        @Label("Event name")
        String eventName;
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10).forEach(i -> remoteApiCall(String.format("%s API", generateRandomString())));
    }

    private static void remoteApiCall(final String apiName) {
        var event = new LongExecutionTime();
        event.begin();
        event.eventName = apiName;
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(30, 60));
        } catch (InterruptedException ie){
            Thread.currentThread().interrupt();
        }
        event.commit();
    }

    private static String generateRandomString(){

        var leftLimit = 97;
        var rightLimit = 122;
        var targetStringLength = 10;
        var random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
