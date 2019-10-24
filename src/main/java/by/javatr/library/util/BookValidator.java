package by.javatr.library.util;

public class BookValidator {

    public static boolean validate(String string, String pattern) {
        return string.matches(pattern);
    }
}
