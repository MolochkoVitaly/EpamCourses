package by.bsu.javacodeparser.parser;

import by.bsu.javacodeparser.entity.CompositeElement;
import by.bsu.javacodeparser.entity.CompositeType;
import by.bsu.javacodeparser.storage.LiteralsAndKeyWords;
import by.bsu.javacodeparser.util.FileUtil;


public class JavaParser {
    private CompositeElement element;

    public void parseText(String fileName) {
        LiteralsAndKeyWords.loadKeyWords();
        LiteralsAndKeyWords.loadLiterals();
        JavaFileHandler javaFileHandler = new JavaFileHandler(CompositeType.FILE);
        element = javaFileHandler.chain(FileUtil.readFile(fileName));
    }

    public CompositeElement getElement() {
        return element;
    }
}

