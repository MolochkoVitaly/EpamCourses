package by.bsu.javacodeparser.action;


import java.util.ArrayList;

public class SortLexemeList {
    public static ArrayList<String> sortLexemesByFirstSymbol(ArrayList<String> lexemes) {
        lexemes.sort((o1, o2) -> Character.toString(o1.charAt(0)).compareToIgnoreCase(Character.toString(o2.charAt(0))));
        return lexemes;
    }
}

