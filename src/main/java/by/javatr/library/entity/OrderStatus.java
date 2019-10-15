package by.javatr.library.entity;

public enum OrderStatus {
    NEW("new"), ACTIVE("active"), CLOSE("close");

    private String action;

    public String getAction() {
        return action;
    }

    OrderStatus(String action) {
        this.action = action;
    }
}
