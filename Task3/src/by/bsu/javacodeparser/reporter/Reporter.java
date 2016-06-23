package by.bsu.javacodeparser.reporter;


import org.apache.log4j.Logger;

public class Reporter {
    private static final Logger LOG = Logger.getLogger(Reporter.class);

    public static void writeComponentInFile(boolean result) {
        LOG.info(result ? "Component has been successfully written to a file" :
                                    "Component has not been recorded in the file");

    }

    public static void writeSortedOperators(boolean result) {
        LOG.info(result ? "Sorted operators have been successfully written to a file" :
                "Sorted operators have not been recorded in the file");
    }

    public static void writeSortedLexemesByFirstSymbol(boolean result) {
        LOG.info(result ? "Sorted lexemes have been successfully written to a file" :
                "Sorted lexemes have not been recorded in the file");
    }

    public static void writeConvertedLexemes(boolean result) {
        LOG.info(result ? "Converted lexemes have been successfully written to a file" :
                "Converted lexemes have not been recorded in the file");
    }
}
