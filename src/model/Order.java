package model;

public class Order {

    private int orderId;
    private int userId;

    public Order(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}