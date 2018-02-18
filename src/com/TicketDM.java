package com;

import sun.tools.jconsole.Tab;

import javax.swing.table.TableModel;
import java.sql.SQLException;

/**
 * Created by edieye on 2017-03-29.
 */
public class TicketDM {

    public void refundTicket(int ticketNumber) {
        int ticket = ConnectionConfiguration.executeStatement(
                "UPDATE event_create ec" +
                        " INNER JOIN has_ticket ht on ec.eventID = ht.eventID" +
                        " SET ec.ticSold = ec.ticSold-1" +
                        " WHERE ht.ticketNum ='" + Integer.toString(ticketNumber) + "';");
        int ticket2 = ConnectionConfiguration.executeStatement(
                "DELETE FROM ticket WHERE ticketNum = '" + Integer.toString(ticketNumber) + "';");
    }



    public boolean ticketExists(int ticketNumber) {
        TableModel exists = null;
        try {
            exists = ConnectionConfiguration.submitQuery(
                    "select * from ticket where ticketNum = '" + ticketNumber + "'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(exists.getRowCount() == 1);
    }



    public TableModel viewAvailableTickets() {
        ConnectionConfiguration.getConnection();
        TableModel availableTickets = null;
        String sqlStatement = "SELECT distinct ec.title, ec.dateTime from event_create ec, has_ticket tc where ec.eventID = tc.eventID AND ec.ticSold < ec.capacity  AND ( select ticPrice from ticket t where t.ticketNum = tc.ticketNum GROUP BY t.ticPrice ORDER by t.ticPrice)";
        try {
            availableTickets = ConnectionConfiguration.submitQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionConfiguration.endConnection();
        return availableTickets;
    }

    public TableModel buyCheapestTicket() {
        TableModel cheapestTicket = null;
        String sqlStatement = "select MIN(t.ticPrice) as ticketPrice from ticket t, has_ticket tc where t.ticketNum = tc.ticketNum and (select COUNT(*) from event_create e where e.eventID = tc.eventID AND e.capacity > e.ticSold GROUP by e.vID);";
        try {
            cheapestTicket = ConnectionConfiguration.submitQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cheapestTicket;
  }

    public void buyTicket(String eventName, String ticketType) {
        TableModel ticketCost = null;
        String sqlStatement = "";
        try {
            ticketCost = ConnectionConfiguration.submitQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TableModel lookAtSumOfTicketsPurchased(int custID) {
        TableModel ticketCost = null;
        String sqlStatement = "select SUM(ticsPurchased) as numberTicketsPurchased from ticket where custID = '" +custID + "'";
        try {
            ticketCost = ConnectionConfiguration.submitQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketCost;
    }
}