package by.bsu.authorization.util;


public class LoginPasswordValidator {

    public static boolean isValid(String login, String password) {
        return login.equals(password) && !login.equals("") && !password.equals("");
    }
}