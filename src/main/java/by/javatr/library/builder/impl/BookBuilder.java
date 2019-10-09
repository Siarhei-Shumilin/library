package by.javatr.library.builder.impl;

import by.javatr.library.builder.Builder;
import by.javatr.library.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookBuilder implements Builder<Book> {
    @Override
    public Book build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String genre = resultSet.getString("genre");
        String description = resultSet.getString("description");
        int numberOfInstances = resultSet.getInt("number_instances");
        int numberAvailableInstances = resultSet.getInt("number_available_instances");
        Book book = new Book(id, title, author, genre, description, numberOfInstances, numberAvailableInstances);
        return book;
    }
}
