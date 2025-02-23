package com.schoolused.entry;

public class orders {//订单实体类
    private int buyers_id;
    private int seller_id;
    private String order_id;
    private String create_time;
    private int com_id;
    private long amount;
    private int already_paid;
    private int quantity;
    private String state;
    private commodity commodity;
    private RegularUsers buyers;

    public int getBuyers_id() {
        return buyers_id;
    }

    public void setBuyers_id(int buyers_id) {
        this.buyers_id = buyers_id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getState() {//
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAlready_paid() {
        return already_paid;
    }

    public void setAlready_paid(int already_paid) {
        this.already_paid = already_paid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public com.schoolused.entry.commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(com.schoolused.entry.commodity commodity) {
        this.commodity = commodity;
    }

    public RegularUsers getBuyers() {
        return buyers;
    }

    public void setBuyers(RegularUsers buyers) {
        this.buyers = buyers;
    }

    @Override
    public String toString() {
        return "orders{" +
                "buyers_id=" + buyers_id +
                ", seller_id=" + seller_id +
                ", order_id='" + order_id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", com_id=" + com_id +
                ", amount=" + amount +
                ", already_paid=" + already_paid +
                ", quantity=" + quantity +
                ", state='" + state + '\'' +
                ", commodity=" + commodity +
                ", buyers=" + buyers +
                '}';
    }
}
