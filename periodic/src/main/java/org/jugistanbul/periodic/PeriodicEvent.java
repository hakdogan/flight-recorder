package org.jugistanbul.periodic;

import jdk.jfr.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Scanner;

/**
 * @author hakdogan (huseyin.akdogan@patikaglobal.com)
 * Created on 20.03.2022
 ***/
public class PeriodicEvent
{
    @Name("org.jugistanbul.ChangeDetector")
    @Label("Change Detector")
    @Period("1 s")
    static class DirectoryWatcher extends Event {
        @Label("Change Detect")
        boolean change;
    }

    static FileTime lastModifiedTime;

    public static void main(String[] args) {

        Runnable hook = createHook();
        FlightRecorder.addPeriodicEvent(DirectoryWatcher.class, hook);

        Scanner scanner = new Scanner(System.in);
        System.out.println("You can terminate the periodic event by pressing any key...");
        scanner.nextLine();

        FlightRecorder.removePeriodicEvent(hook);
    }

    static Runnable createHook(){

        return () -> {

            DirectoryWatcher directoryWatcher = new DirectoryWatcher();
            try {
                var path = Paths.get("file", "change.log");
                BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);

                if(null != lastModifiedTime
                        && lastModifiedTime.compareTo(attr.lastModifiedTime()) != 0){
                    directoryWatcher.change = true;
                }

                lastModifiedTime = attr.lastModifiedTime();
                directoryWatcher.commit();
            } catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        };
    }
}
