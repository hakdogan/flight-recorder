package org.jugistanbul.profile;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author hakdogan (hakdogan75@gmail.com)
 * Created on 16.09.2025
 ***/
public class Caller {

    void main() {

        IntStream.range(0, 5).forEach(index -> {
            var sleep = ThreadLocalRandom.current().nextInt(index * 10, 500);
            try {
                TimeUnit.MILLISECONDS.sleep(sleep);
                RemoteProcedure.call();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

}
