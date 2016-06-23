package by.bsu.javacodeparser.action;

import java.util.ArrayList;


public class DeleteSymbolInList {
    public static ArrayList<String> deleteFirstSymbol(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        for (String aList : list) {
            newList.add(aList.replace(Character.toString(aList.charAt(0)), ""));
        }
        return newList;
    }
}

