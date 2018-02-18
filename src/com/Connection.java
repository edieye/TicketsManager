package com;

/**
 * Created by edieye on 2017-03-29.
 */

import sun.tools.jconsole.Tab;

import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by edieye on 2017-03-28.
 */

//use this to test for basic SQL statments

public class Connection {
    public static void main(String [] args){

        //THIS IS FOR EMPLOYEE
        EmployeeDM edi = new EmployeeDM();
        edi.selectAll("SELECT * FROM employee WHERE eid = 3");
        TicketDM tdi = new TicketDM();
        //TableModel sum  = tdi.lookAtSumOfTicketsPurchased();
        //tdi.lookAtSumOfTicketsPurchased();
        //System.out.println(sum);



        //THIS CREATES A TABLE
//        edi.createEmployeeTable();
        //Employee employee = new Employee(34242, "FAK34E NAME", "CLERK???");
        //edi.insert(employee);

//        int returned =edi.addEmployee(12);
//        System.out.println(returned);
       // System.out.println(employee.getEid() + "," + employee.getName() + "," + employee.getEmpType());

        //THIS GETS AN EMPLOYEE USING PRIAMRY KEY
        //Employee employee = edi.selectById(12);
        //System.out.println(employee.getEid() + "," + employee.getName() + "," + employee.getEmpType());


        //THIS DELETES AN EMPLOYE USING EID
      //  edi.delete(2);
//        Employee employee = new Employee("hellohi", "HI MELANIE");
//        //THIS UPDATES AN EMPLOYEE USING EID
//        edi.update(employee ,3);

        //THIS GETS ALL EMPLOYEES
        //String[][] employees = edi.getEmployeeResults("SELECT * FROM employee");
//        List<Employee> employees = edi.selectAll();
//        for (Employee employee1: employees){
//            System.out.println(employee1.getEid() + "," + employee1.getName() + "," + employee1.getEmpType());
//        }

//        TicketDM tdi = new TicketDM();
//        int results = tdi.lookAtSumOfTicketsPurchased(3);



    }

}