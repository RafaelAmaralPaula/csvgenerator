package com.rafaelamaral.csvgeneratorapp.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class FileUtils {

    private FileUtils(){}

    public static String createdDirectory(String name) throws IOException {
        Path path = Paths.get(name);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        return name;
    }

    public static List<String> getContentsAllFiles() throws IOException {
        File folder = new File("queries");
        List<String> files = new ArrayList<>();
        for (int i = 0; i < folder.listFiles().length; i++) {
            files.add(folder.listFiles()[i].getName());
        }
        return files;
    }

    public static Map<String, String> getContentFile() throws IOException {
        Map<String, String> objects = new HashMap<>();

        FileUtils.getContentsAllFiles().forEach(fileName -> {
            Path path = Paths.get("queries/" + fileName);
            StringBuilder sb = new StringBuilder();
            try {
                readAllLines(path).forEach(line -> sb.append(line.concat(" ")));
            } catch (IOException ignore) {}

            objects.put(fileName, sb.toString());
        });
        return objects;
    }

}
