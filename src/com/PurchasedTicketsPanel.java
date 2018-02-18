package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cleotracey on 2017-03-30.
 */
public class PurchasedTicketsPanel {
    private JTable PurchasedTicketsTable;
    private JPanel panel1;
    private JTextField RefundTicketInput;
    private JButton RefundTicketButton;
    private int RefundTicketNumber;

    public PurchasedTicketsPanel() {
        RefundTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RefundTicketNumber = Integer.parseInt(RefundTicketInput.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input");
                }
                TicketDM dm = new TicketDM();
                dm.refundTicket(RefundTicketNumber);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Ticket " + RefundTicketNumber + " refunded.");
            }
        });
    }
}
