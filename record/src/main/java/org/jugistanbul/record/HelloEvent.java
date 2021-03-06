package org.jugistanbul.record;

import jdk.jfr.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author hakdogan (huseyin.akdogan@patikaglobal.com)
 * Created on 12.03.2022
 ***/
public class HelloEvent
{
    @Name("org.jugistanbul.Hello")
    @Label("Hello Event")
    @Description("This example demonstrates the monitoring of events consisting of messages sent at a random time.")
    @Category({ "Demonstration", "Tutorial" })
    static class Hello extends Event {
        @Label("Message")
        String message;
    }

    private static final Hello HELLO_EVENT = new Hello();

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10).forEach(HelloEvent::fireEvent);
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
}
