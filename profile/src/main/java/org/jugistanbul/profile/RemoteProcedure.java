package org.jugistanbul.profile;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author hakdogan (hakdogan75@gmail.com)
 * Created on 16.09.2025
 ***/
public class RemoteProcedure {

    public static void call() {
        var sleep = ThreadLocalRandom.current().nextInt(100 , 500);
        try {
            TimeUnit.MILLISECONDS.sleep(sleep);
            System.out.println("Calling remote procedure");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
