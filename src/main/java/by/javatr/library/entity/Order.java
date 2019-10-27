package by.javatr.library.entity;

public class Order {
    private int id;
    private String name;
    private String title;
    private String date;
    private OrderStatus status;
    private int userId;
    private int bookId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public static class Builder {
        private Order order;

        public Builder() {
            order = new Order();
        }

        public Builder buildId(int id){
            order.id = id;
            return this;
        }
        public Builder buildName(String name) {
            order.name = name;
            return this;
        }

        public Builder buildTitle(String title) {
            order.title = title;
            return this;
        }

        public Builder buildDate(String date) {
            order.date = date;
            return this;
        }

        public Builder buildActive(OrderStatus status) {
            order.status = status;
            return this;
        }

        public Builder buildUserId(int userId) {
            order.userId = userId;
            return this;
        }

        public Builder buildBookId(int bookId) {
            order.bookId = bookId;
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
