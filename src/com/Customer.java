package com;

/**
 * Created by edieye on 2017-03-29.
 */
public class Customer {
    private int custID;
    private String name;
    private int attended;

    public Customer(int custID, String name, int attended) {
        this.custID = custID;
        this.name = name;
        this.attended = attended;

    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttended() {
        return attended;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }
}
