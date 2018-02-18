package com;

import javax.swing.table.TableModel;
import java.sql.*;
import java.sql.Connection;

/**
 * Created by edieye on 2017-03-29.
 */


public class ConnectionConfiguration {
    private static Connection connection;
    private static Statement statement;
    //everytime we connect to MySql, we need to use this connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //this is driver to translate
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketmanager?autoReconnect=true&useSSL=false", "root", "snek");


        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void endConnection() {
        if (statement != null) {
            try {
                statement.close();
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

    public static int executeStatement(String state){   //THIS IS EXECUTE UPDATE
        Connection connection = ConnectionConfiguration.getConnection();
        int noOfRows = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(state);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionConfiguration.endConnection();
        }
        return noOfRows;
    }

    public static TableModel submitQuery(String state) throws SQLException {
        Connection connection = ConnectionConfiguration.getConnection();
        int noOfRows = 0;
        ResultSet resultSet = null;
        TableModel tablemodel = null;
        String[][] data = new String[1][1];
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(state);
            tablemodel = ResultSetTable.resultSetToTableModel(resultSet);

            //-----------THIS PRINTS THE TABLE TO VERIFY IT WORKS--------
            int rowCount = tablemodel.getRowCount();
            int columnCount = tablemodel.getColumnCount();

           // for (int row = 0; row < rowCount; row++) {
             //   for (int column = 0; column < columnCount; column++) {
                    //System.out.println(tablemodel.getValueAt(row, column) + ", ");
                }
                
            }
            //---------------------------
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            ConnectionConfiguration.endConnection();
        }
        return tablemodel;
    }












}