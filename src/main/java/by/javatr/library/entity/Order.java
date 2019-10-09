package by.javatr.library.entity;

public class Order {
    private int id;
    private String name;
    private String title;
    private String date;
    private boolean active;
    private int userId;
    private int bookId;

//    public Order() {
//    }
//
//    public Order(int userId, int bookId, String date) {
//        this.userId = userId;
//        this.bookId = bookId;
//        this.date = date;
//    }
//
//    public Order(int id, String name, String title, boolean active, String date, int bookId) {
//        this.id = id;
//        this.name = name;
//        this.title = title;
//        this.active = active;
//        this.date = date;
//        this.bookId = bookId;
//    }

    public int getUserId() {
        return userId;
    }

//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public int getBookId() {
        return bookId;
    }

//    public void setBookId(int bookId) {
//        this.bookId = bookId;
//    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    public static /*static*/ class Builder {
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

        public Builder buildActive(boolean active) {
            order.active = active;
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
