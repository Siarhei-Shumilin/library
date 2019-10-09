package by.javatr.library.util;

import by.javatr.library.entity.Book;

public class BookValidator {

    private static final String PATTERN_BOOK = "[A-Za-zА-Яа-яЁё0-9_\\s.,!?\";:]{1,30}";
    private static final String DESCRIPTION = "[A-Za-zА-Яа-яЁё0-9_\\s.,!?\";:]{1,500}";


    public static boolean validateBook(Book book) {
        boolean valid = false;
        if (book.getTitle().matches(PATTERN_BOOK) && book.getAuthor().matches(PATTERN_BOOK) &&
                book.getGenre().matches(PATTERN_BOOK) && book.getDescription().matches(DESCRIPTION)) {
            valid = true;
        }
        return valid;
    }
}
