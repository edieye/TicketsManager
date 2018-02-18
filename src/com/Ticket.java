package com;

/**
 * Created by edieye on 2017-03-29.
 */
public class Ticket {
    private int ticketNum;
    private int seatNum;
    private String ticType;
    private String section;
    private int custID;
    private int confirmNum;
    private int ticsPurchased;

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public String getTicType() {
        return ticType;
    }

    public void setTicType(String ticType) {
        this.ticType = ticType;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public int getConfirmNum() {
        return confirmNum;
    }

    public void setConfirmNum(int confirmNum) {
        this.confirmNum = confirmNum;
    }

    public int getTicsPurchased() {
        return ticsPurchased;
    }

    public void setTicsPurchased(int ticsPurchased) {
        this.ticsPurchased = ticsPurchased;
    }
}
