package by.javatr.library.builder.impl;

import by.javatr.library.builder.Builder;
import by.javatr.library.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderBuilder implements Builder {
    @Override
    public Order build(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String title = resultSet.getString("title");
        boolean active = resultSet.getBoolean("isActive");
        String date = resultSet.getString("date");
        int bookId = resultSet.getInt("book_id");
        Order order = new Order.Builder()
        .buildId(id)
        .buildName(name)
        .buildTitle(title)
        .buildActive(active)
        .buildDate(date)
        .buildBookId(bookId)
        .build();
        return order;
    }
}
