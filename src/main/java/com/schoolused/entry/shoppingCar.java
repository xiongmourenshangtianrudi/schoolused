package com.schoolused.entry;

public class shoppingCar {
    private int id;
    private int userid;
    private int com_id;
    private int quantity;
    private commodity commodity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
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

    @Override
    public String toString() {
        return "shoppingCar{" +
                "id=" + id +
                ", userid=" + userid +
                ", com_id=" + com_id +
                ", quantity=" + quantity +
                ", commodity=" + commodity +
                '}';
    }
}
