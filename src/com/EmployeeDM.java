package com;

import com.Employee;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import sun.tools.jconsole.Tab;


import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableModel;

/**
 * Created by edieye on 2017-03-29.
 */
public class EmployeeDM{

    public int deleteEmployee(int eid) {
        int employee = ConnectionConfiguration.executeStatement("DELETE FROM employee WHERE eid = " + eid);
        return employee;
    }

    public TableModel selectAll(String query){
        ConnectionConfiguration.getConnection();
        TableModel allEmployees = null;
        try {
            allEmployees = ConnectionConfiguration.submitQuery(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionConfiguration.endConnection();
        return allEmployees;
    }


    public TableModel getEmployeeResults(String query) {
        TableModel employees = null;
        try {
            employees = ConnectionConfiguration.submitQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return employees;
    }

    public boolean employeeExists(int employeeID) {
        TableModel exists = null;
        try {
            exists = ConnectionConfiguration.submitQuery(
                    "select * from employee where eID = '" + employeeID + "'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(exists.getRowCount() == 1);
    }

    public int addEmployee(int employeeID) {
        String sqlStatement = "SELECT * FROM employee WHERE eid = ?"; // TODO
        int totalEmployees = ConnectionConfiguration.executeStatement(sqlStatement);
        return totalEmployees;
    }

    public void createEmployeeTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS test (eid int primary key unique auto_increment," + "first_name varchar(55), last_name varchar(55))");

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if (statement != null){
                try {
                    statement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void insert(Employee employee) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO employee (eid, name, empType)" + "VALUES (? , ?, ?)");
            preparedStatement.setInt(1, employee.getEid());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getEmpType());
            preparedStatement.executeUpdate();
           // System.out.println("IT INSERTED..");// / SQL STATMEENT HERE

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public Employee selectById(int eid) {
        Employee employee = new Employee();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE eid = ?");
            preparedStatement.setInt(1, eid); // column number, the
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                employee.setEid(resultSet.getInt("eid"));//name column
                employee.setName(resultSet.getString("name"));
                employee.setEmpType(resultSet.getString("empType"));
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
        finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employee;

    }


    public boolean eventExists(int eventID) {
        TableModel exists = null;
        try {
            exists = ConnectionConfiguration.submitQuery(
                    "select * from event_create where eventID = '" + eventID + "'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(exists.getRowCount() == 1);
    }



    public void delete(int eid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE eid = ?");
            preparedStatement.setInt(1, eid);
            preparedStatement.executeUpdate();
            //System.out.println("DELETE FROM employee WHERE eid = ?");

        }
        catch(Exception e){
            e.printStackTrace();

        }
        finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void update(Employee employee, int eid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE employee SET " + "name = ?, empType = ? WHERE eid = ?");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmpType());
            preparedStatement.setInt(3, eid);
            preparedStatement.executeUpdate();
          

        }
        catch(Exception e){
            e.printStackTrace();

        }
        finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public TableModel viewPurchasesByEvent(int eventID) {
        ConnectionConfiguration.getConnection();
        String sqlStatement =
                "SELECT distinct t.custID, ec.eventID, ec.title, ec.dateTime " +
                        "FROM ((ticket t " +
                        "INNER JOIN has_ticket ht on ht.ticketNum = t.ticketNum) " +
                        "INNER JOIN event_create ec on ht.eventID = ec.eventID) " +
                        "WHERE ec.eventID = '" + Integer.toString(eventID) + "';";
        TableModel purchases = null;
        try {
            purchases = ConnectionConfiguration.submitQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionConfiguration.endConnection();
        return purchases;
    }

    public TableModel viewCustomer(int customerID) {
        TableModel customer = null;
        try {
            customer = ConnectionConfiguration.submitQuery(
                    "select c.custID, c.name, ci.phoneNum, ci.addr from customer c, customer_info ci where c.custID = ci.custID AND c.custID = " + customerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean employeeLogin(String username, String password) {
        TableModel login = null;
        try {
            login = ConnectionConfiguration.submitQuery(
                    "select * from userNames where username = '" + username.toString() + "' and pass = '" + password.toString() +"'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !(login.getRowCount() == 1);
    }
}
