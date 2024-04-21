/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Login;



public class LoginDao {

    private DBDao dbDao;

    public LoginDao() {
        this.dbDao = new DBDao();
    }

    public boolean validate(Login login) throws ClassNotFoundException {
        boolean status = false;

        try (Connection connection = dbDao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE username = ? AND password = ?")) {

            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            System.out.println("status");

        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            dbDao.closeConnection();
        }
        return status;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }

}
    }
}