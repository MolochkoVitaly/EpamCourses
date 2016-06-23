package by.bsu.javacodeparser.storage;


import java.util.ArrayList;

public class LexemeList {
    private static ArrayList<String> list = new ArrayList<>();

    public LexemeList() {
    }

    public static void addLexeme(String lexeme) {
        list.add(lexeme);
    }

    public static ArrayList<String> getList() {
        return new ArrayList<>(list);
    }
}
