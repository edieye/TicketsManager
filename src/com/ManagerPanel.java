package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cleotracey on 2017-03-27.
 */
public class ManagerPanel extends JPanel {
    public JPanel panel2;
    private JTextArea ViewPurchasesInput;
    private JTextField AddEmployeeInput;
    private JTextField DeleteEmployeeInput;
    private JButton AddEmployeeButton;
    private JButton DeleteEmployeeButton;
    private JButton ViewPurchasesButton;
    private JTextField ViewCustomerInput;
    private JButton ViewCustomerButton;
    private JTextField EmployeeNameInput;
    private JTextArea EmployeeTypeInput;
    private JLabel ViewTicketSales;
    private JTable TicketSalesTable;
    private JTable ViewCustomerTable;
    private JButton DivisionCustomerButton;
    private JTable DivisionCustomerTable;
    public int eventID;
    public int employeeID;
    public String employeeName;
    public String employeeType;
    public int customerID;
    private TableModel model;

    public ManagerPanel() {

        ViewPurchasesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eventID = Integer.parseInt(ViewPurchasesInput.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input");
                    return;
                }
                EmployeeDM dm = new EmployeeDM();
                if (!dm.eventExists(eventID)) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Event " + eventID + " does not exist.");
                    return;
                }
                model = dm.viewPurchasesByEvent(eventID);
                TicketSalesTable.setModel(model);
            }
        });

        AddEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    employeeID = Integer.parseInt(AddEmployeeInput.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input");
                    return;
                }
                employeeType = EmployeeTypeInput.getText();
                employeeName = null;

                try {
                    Integer.parseInt(EmployeeNameInput.getText());
                }
                catch (NumberFormatException ex) {
                    // this means input is a string. which is what we want
                    employeeName = EmployeeNameInput.getText();
                }
                if (employeeName == null) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input");
                    return;
                }
                if (employeeName.length() == 0 || employeeType.length() == 0) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please fill out all fields");
                    return;
                }
                if (!employeeType.toLowerCase().equals("clerk") && !employeeType.toLowerCase().equals("manager")) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input");
                    return;
                }
                EmployeeDM dm = new EmployeeDM();
                Employee emp = new Employee(employeeID,employeeName,employeeType);
                dm.insert(emp);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Employee " + employeeID + " added.");
            }
        });

        DeleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    employeeID = Integer.parseInt(DeleteEmployeeInput.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input");
                    return;
                }
                EmployeeDM dm = new EmployeeDM();
                if (!dm.employeeExists(employeeID)) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Employee " + employeeID + " does not exist.");
                    return;
                }
                else {
                    dm.delete(employeeID);
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Employee " + employeeID + " deleted.");
                }
            }
        });

        ViewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    customerID = Integer.parseInt(ViewCustomerInput.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input");
                    return;
                }
                CustomerDM cm = new CustomerDM();
                if (!cm.customerExists(customerID)) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Customer " + customerID + " does not exist.");
                    return;
                }
                else {
                    EmployeeDM dm = new EmployeeDM();
                    model = dm.viewCustomer(customerID);
                    ViewCustomerTable.setModel(model);
                }
            }
        });
        DivisionCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerDM dm = new CustomerDM();
                model = dm.viewLoyalCustomer();
                DivisionCustomerTable.setModel(model);
            }
        });
    }

}
