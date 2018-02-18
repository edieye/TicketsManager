package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Created by cleotracey on 2017-03-27.
 */
public class CustomerPanel extends JPanel {
    public JPanel panel1;
    private JButton FindAvailableTicketsButton;
    private JButton ViewMyTicketsButton;
    private JTextField ViewTicketsInput;
    private JTable ViewMyTicketsTable;
    private JTextField RefundTicketInput;
    private JButton RefundTicketButton;
    private JTable AggregationTable;
    private JLabel NumTicketsPurchased;
    int RefundTicketNumber;
    int customerID;
    TableModel model;
    TableModel model1;

    public CustomerPanel(){

        FindAvailableTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ticketFrame = new JFrame("Available Tickets");
                TicketPanel ticketCard = new TicketPanel();
                ticketFrame.getContentPane().add(ticketCard.panel1);
                ticketFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                ticketFrame.setLocation(300, 300);
                ticketFrame.pack();
                ticketFrame.setVisible(true);
            }
        });
        ViewMyTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    customerID = Integer.parseInt(ViewTicketsInput.getText());
                    CustomerDM dm = new CustomerDM();
                    if (!dm.customerExists(customerID)) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Customer " + customerID + " does not exist.");
                        return;
                    }
                    else {
                        TicketDM tm = new TicketDM();
                        model = dm.viewTickets(customerID);
                        ViewMyTicketsTable.setModel(model);
                        model1 = tm.lookAtSumOfTicketsPurchased(customerID);
                        AggregationTable.setModel(model1);
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input");
                    return;
                }
            }
        });
        RefundTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RefundTicketNumber = Integer.parseInt(RefundTicketInput.getText());
                    TicketDM dm = new TicketDM();
                    if (!dm.ticketExists(RefundTicketNumber)) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Ticket " + RefundTicketNumber + " does not exist.");
                        return;
                    }
                    else {
                        dm.refundTicket(RefundTicketNumber);
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Ticket " + RefundTicketNumber + " refunded.");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input");
                    return;
                }
            }
        });
    }
}

