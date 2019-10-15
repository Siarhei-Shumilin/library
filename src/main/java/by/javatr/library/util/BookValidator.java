package by.javatr.library.util;

public class BookValidator {

    private static final String PATTERN_BOOK = "[A-Za-zА-Яа-яЁё0-9_\\s.,!?\";:]{1,30}";
    private static final String DESCRIPTION = "[A-Za-zА-Яа-яЁё0-9_\\s.,!?\";:]{1,500}";
    private static final String NUMBER_OF_INSTANCES = "[0-9]{1,5}";

    public static boolean validate(String string) {
        boolean valid = false;
        if (string.matches(PATTERN_BOOK)) {
            valid = true;
        }
        return valid;
    }
    public static boolean validateDescription(String description) {
        boolean valid = false;
        if (description.matches(DESCRIPTION)) {
            valid = true;
        }
        return valid;
    }
    public static boolean validateNumberOfInstances(String number) {
        boolean valid = false;
        if (number.matches(NUMBER_OF_INSTANCES)) {
            valid = true;
        }
        return valid;
    }
}
