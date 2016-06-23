package by.bsu.javacodeparser.util;

import by.bsu.javacodeparser.entity.Component;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;


public class FileUtil {
    private static final Logger LOG = Logger.getLogger(FileUtil.class);

    private FileUtil() {
    }

    public static String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        String dirName = "files";
        Path path = FileSystems.getDefault().getPath(dirName, fileName);
        Stream<String> stringStream;
        try {
            stringStream = Files.lines(path);
        } catch (IOException e) {
            LOG.fatal("Read file error", e);
            throw new RuntimeException();
        }
        if (stringStream != null) {
            stringStream.forEach(_line -> builder.append(_line).append("\n"));
        }
        return builder.toString();
    }

    public static boolean saveResultInFile(String fileName, Component component) {
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            writer.print(component.toString());
            result = true;
        } catch (IOException ex) {
            LOG.error("Write file error", ex);
        }
        return result;
    }

    public static boolean saveResultInFile(String fileName, ArrayList<String> list) {
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            //list.forEach(writer::println);
            for (String str: list) {
                writer.println(str);
            }
            result = true;
        } catch (IOException ex) {
            LOG.error("Write file error", ex);
        }
        return result;
    }

    public static boolean saveSortedResultInFile(String fileName, ArrayList<String> list) {
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            StringBuilder builder = new StringBuilder();
            builder.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                if (Character.toString(list.get(i).charAt(0)).equalsIgnoreCase(Character.toString(list.get(i-1).charAt(0)))) {
                    builder.append(" ").append(list.get(i));
                } else {
                    builder.append("\n");
                }
            }
            writer.print(builder.toString());
            result = true;
        } catch (IOException ex) {
            LOG.error("Write file error", ex);
        }
        return result;
    }

    public static boolean saveConvertedResultInFile(String fileName, ArrayList<String> list, ArrayList<String> convertedList) {
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i) + " => " + convertedList.get(i);
                writer.println(str);
            }
            result = true;
        } catch (IOException ex) {
            LOG.error("Write file error", ex);
        }
        return result;
    }
}