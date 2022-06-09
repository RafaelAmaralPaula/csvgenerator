package com.rafaelamaral.csvgeneratorapp.util;

import com.rafaelamaral.csvgeneratorapp.model.CustomFile;
import com.rafaelamaral.csvgeneratorapp.model.QueryModel;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;
import static java.util.Objects.requireNonNull;

@Slf4j
public class FileUtils {

    private FileUtils() {
    }

    public static String createdDirectory(String name) {
        Path path = Paths.get(name);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return name;
    }

    public static String makeFile(String path, String fileName) {
        return path + "/" + fileName + ".csv";
    }

    public static List<CustomFile> getContentsAllFiles(String queriesPathName) {
        var folder = new File(queriesPathName);
        var files = new ArrayList<CustomFile>();
        for (int i = 0; i < requireNonNull(folder.listFiles()).length; i++) {
            files.add(new CustomFile(requireNonNull(folder.listFiles())[i].getName()));
        }
        return files;
    }

    public static List<QueryModel> getContentFile(String queriesPathName) {
        var objects = new ArrayList<QueryModel>();
        getContentsAllFiles(queriesPathName).forEach(customFile -> {
            try {
                StringBuilder sb = new StringBuilder();
                readAllLines(Paths.get(queriesPathName + "/" + customFile.getFullName()))
                        .forEach(line -> sb.append(line.concat(" ")));
                objects.add(new QueryModel(customFile.getName(), sb.toString()));
            } catch (IOException e) {
                log.error("Get contentFile Error: ", e);
            }
        });
        return objects;
    }

}
