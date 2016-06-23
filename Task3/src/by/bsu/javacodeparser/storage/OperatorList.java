package by.bsu.javacodeparser.storage;


import java.util.ArrayList;

public class OperatorList {
    private static ArrayList<String> list = new ArrayList<>();

    public OperatorList() {
    }

    public static void addOperator (String operator) {
        list.add(operator);
    }

    public static ArrayList<String> getList() {
        return new ArrayList<>(list);

    }
}
