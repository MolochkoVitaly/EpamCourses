package by.bsu.javacodeparser.storage;


import by.bsu.javacodeparser.manager.ResourceManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class LiteralsAndKeyWords {
    private static final Logger LOG = Logger.getLogger(LiteralsAndKeyWords.class);
    private static ArrayList<String> keyWords = new ArrayList<>();
    private static ArrayList<String> literals = new ArrayList<>();

    public static void loadKeyWords() {
        Path path = FileSystems.getDefault().getPath(ResourceManager.getInstance().getProperty(ResourceManager.DIR_NAME),
                ResourceManager.getInstance().getProperty(ResourceManager.FILE_WITH_KEYS));
        Stream<String> stringStream;
        try {
            stringStream = Files.lines(path);
        } catch (IOException e) {
            LOG.fatal("Can not load key words", e);
            throw new RuntimeException();
        }
        if (stringStream != null) {
            stringStream.forEach(_line -> keyWords.add(_line.trim()));
        }
    }

    public static void loadLiterals() {
        literals.add("null");
        literals.add("true");
        literals.add("false");
    }

    public static ArrayList<String> getKeyWords() {
        return new ArrayList<>(keyWords);
    }

    public static ArrayList<String> getLiterals() {
        return new ArrayList<>(literals);
    }
}
