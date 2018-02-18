package com;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cleotracey on 2017-03-30.
 */
public class TicketPanel {
    private JTable CheapestTicketTable;
    public JPanel panel1;
    private JButton buyCheapestAvailableTicketButton;
    private JTable ViewAvailableTicketsTable;
    private String EventPurchaseInput;
    private TableModel model;

    public TicketPanel() {

        TicketDM dm = new TicketDM();
        model = dm.viewAvailableTickets();
        ViewAvailableTicketsTable.setModel(model);

        buyCheapestAvailableTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicketDM dm = new TicketDM();
                model = dm.buyCheapestTicket();
                CheapestTicketTable.setModel(model);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Cheapest ticket purchased.");
            }
        });

    }
}
