package by.bsu.javacodeparser.main;

import by.bsu.javacodeparser.action.DeleteSymbolInList;
import by.bsu.javacodeparser.action.SortLexemeList;
import by.bsu.javacodeparser.action.SortOperatorList;
import by.bsu.javacodeparser.parser.JavaParser;
import by.bsu.javacodeparser.reporter.Reporter;
import by.bsu.javacodeparser.storage.LexemeList;
import by.bsu.javacodeparser.util.FileUtil;
import by.bsu.javacodeparser.storage.OperatorList;
import by.bsu.javacodeparser.manager.ResourceManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;


public class Main {

    static {
        String pathToFileConfig = "resources\\log4j.xml";
        new DOMConfigurator().doConfigure(pathToFileConfig, LogManager.getLoggerRepository());
    }

    public static void main(String[] args) {
        JavaParser javaParser = new JavaParser();
        javaParser.parseText(ResourceManager.getInstance().getProperty(ResourceManager.TEXT_IN_FILE_NAME));
        Reporter.writeComponentInFile(FileUtil.saveResultInFile((ResourceManager.getInstance().
                getProperty(ResourceManager.TEXT_OUT_FILE_NAME)), javaParser.getElement()) );
        Reporter.writeSortedOperators(FileUtil.saveResultInFile((ResourceManager.getInstance().
                getProperty(ResourceManager.SORTED_OPERATORS_OUT_FILE_NAME)),
                SortOperatorList.sortOperators(OperatorList.getList())));
        Reporter.writeSortedLexemesByFirstSymbol(FileUtil.saveSortedResultInFile((ResourceManager.getInstance().
                getProperty(ResourceManager.SORTED_LEXEMES_OUT_FILE_NAME)),
                SortLexemeList.sortLexemesByFirstSymbol(LexemeList.getList())));
        Reporter.writeConvertedLexemes(FileUtil.saveConvertedResultInFile(ResourceManager.getInstance().
                getProperty(ResourceManager.CONVERTED_LEXEMES_OUT_FILE_NAME), LexemeList.getList(),
                DeleteSymbolInList.deleteFirstSymbol(LexemeList.getList())));
    }
}
