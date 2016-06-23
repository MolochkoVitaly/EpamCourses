package by.bsu.javacodeparser.action;


import java.util.ArrayList;

public class SortOperatorList {
    public static ArrayList<String> sortOperators(ArrayList<String> operators) {
        operators.sort((o1, o2) -> o1.split(" ").length - o2.split(" ").length);
        return operators;
    }
}
