package com;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

/**
 * Created by edieye on 2017-03-26.
 */
public class App {
    private JPanel panelMain;
    private JButton buttonCustomer;
    private JButton buttonManager;
    private JPasswordField PasswordInput;
    private JTextField UsernameInput;
    private JButton CustomerLoginButton;
    private JButton EmployeeLoginButton;
    private static JFrame frame;
    private String username;
    private String password;

    public App() {
        CustomerLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = UsernameInput.getText();
                char[] temp = PasswordInput.getPassword();
                password = new String(temp);
                CustomerDM dm = new CustomerDM();
                if (!dm.customerLogin(username, password)) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid login. Please try again.");
                    return;
                }
                JFrame customerFrame = new JFrame("Customer Window");
                CustomerPanel custCard = new CustomerPanel();
                customerFrame.getContentPane().add(custCard.panel1);
                customerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                customerFrame.setLocation(300, 300);
                customerFrame.pack();
                customerFrame.setVisible(true);
            }
        });


        EmployeeLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = UsernameInput.getText();
                char[] temp = PasswordInput.getPassword();
                password = new String(temp);
                EmployeeDM dm = new EmployeeDM();
                if (!dm.employeeLogin(username, password)) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid login. Please try again.");
                    return;
                }
                JFrame managerFrame = new JFrame("Employee Window");
                ManagerPanel managerCard = new ManagerPanel();
                managerFrame.getContentPane().add(managerCard.panel2);
                managerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                managerFrame.setLocation(300, 300);
                managerFrame.pack();
                managerFrame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Login");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(300, 300);
        frame.pack();
        frame.setVisible(true);
    }
}
