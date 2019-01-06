package com.haroldjin.java.lang.fundamental;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReadingExamples {

    public static List<String> readFileBufferReader(String filePath) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        }
    }

    public static List<String> readFileFileReader(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);

        int i;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> lines = new ArrayList<>();
        while((i = fileReader.read()) != -1){
            if ((char)i != '\n') {
                stringBuilder.append((char)i);
            } else {
                lines.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        }
        return lines;
    }


    public static List<String> readFileScanner(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()){
            lines.add(scanner.nextLine());
        }

        return lines;
    }

    public static List<String> readFileFilesPath(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }


}
