package com.rafaelamaral.csvgeneratorapp.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;
import static java.util.Objects.requireNonNull;

public class FileUtils {

    private FileUtils() {
    }

    public static String createdDirectory(String name) throws IOException {
        Path path = Paths.get(name);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        return name;
    }

    public static List<String> getContentsAllFiles(String queriesPathName) {
        var folder = new File(queriesPathName);
        var files = new ArrayList<String>();
        for (int i = 0; i < requireNonNull(folder.listFiles()).length; i++) {
            files.add(requireNonNull(folder.listFiles())[i].getName());
        }
        return files;
    }

    public static Map<String, String> getContentFile(String queriesPathName) {
        var objects = new HashMap<String, String>();
        getContentsAllFiles(queriesPathName).forEach(fileName -> {
            try {
                StringBuilder sb = new StringBuilder();
                readAllLines(Paths.get(queriesPathName + "/" + fileName))
                        .forEach(line -> sb.append(line.concat(" ")));
                objects.put(fileName, sb.toString());
            } catch (IOException ignore) {
            }
        });
        return objects;
    }

}
