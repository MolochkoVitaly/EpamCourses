package by.bsu.office.util;


public class IdGenerator {
    private static int id;

    static {
        id = 1;
    }

    public IdGenerator() {
    }

    public static int getId() {
        return id++;
    }
}
