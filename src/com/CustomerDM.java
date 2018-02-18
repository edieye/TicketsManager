package com;

import javax.swing.table.TableModel;
import java.sql.SQLException;

/**
 * Created by edieye on 2017-03-28.
 */
public class CustomerDM {

    public void viewCustomer(int customerID) {

    }

    public TableModel viewTickets(int customerID) {
        ConnectionConfiguration.getConnection();
        String sqlStatement = "select t.ticketNum, e.title from ticket t, event_create e, has_ticket ht where t.custID = " + customerID + " AND t.ticketNum = ht.ticketNum AND e.eventID = ht.eventID";
        TableModel purchases = null;
        try {
            purchases = ConnectionConfiguration.submitQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionConfiguration.endConnection();
        return purchases;
    }

    public boolean customerExists(int customerID) {
        TableModel exists = null;
        try {
            exists = ConnectionConfiguration.submitQuery(
                    "select * from customer where custID = '" + customerID + "'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(exists.getRowCount() == 1);
    }

    public boolean customerLogin(String username, String password) {
        TableModel login = null;
        try {
            login = ConnectionConfiguration.submitQuery(
                    "select username, pass from userNames where username = '" + username.toString() + "' and pass = '" + password.toString() +"'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(login.getRowCount() == 1);
    }

    public TableModel viewLoyalCustomer() {
        ConnectionConfiguration.getConnection();
        String sqlStatement = "select custID from customer c where not exists (select distinct eventID from event_create e where not exists (select c.custID from ticket t, has_ticket ht where e.eventID = ht.eventID  AND ht.ticketNum = t.ticketNum AND c.custID = t.custID))" ; 
        TableModel customer = null;
        try {
            customer = ConnectionConfiguration.submitQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionConfiguration.endConnection();
        return customer;
    }
}

