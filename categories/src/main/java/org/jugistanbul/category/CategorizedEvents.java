package org.jugistanbul.category;

import jdk.jfr.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hakdogan (huseyin.akdogan@patikaglobal.com)
 * Created on 13.03.2022
 ***/
public class CategorizedEvents
{

    private static final String CATEGORY = "JUG_Istanbul";
    private static final String FILE_READ = "File Read";
    private static final String LIST_FILES = "List Files";

    @Name("com.oracle.FileRead")
    @Label(FILE_READ)
    @Category({CATEGORY, FILE_READ})
    private static class FileRead extends Event {
        @DataAmount
        int bytesReader;
    }

    @Name("com.oracle.FileList")
    @Label(LIST_FILES)
    @Category({CATEGORY, LIST_FILES})
    private static class FileList extends Event {}

    public static void main(String[] args) {

        FileList fileList = new FileList();
        fileList.begin();
        Path resourceDirectory = Paths.get("file");
        var dir = resourceDirectory.toAbsolutePath().toString();
        Set<String> files = listFiles(dir);
        fileList.commit();

        FileRead fileRead = new FileRead();
        fileRead.begin();
        files.forEach(file -> {
            try {
                fileRead.bytesReader += Files.readString(Path.of(String.join("/", dir, file))).getBytes().length;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fileRead.commit();
    }

    private static Set<String> listFiles(final String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
