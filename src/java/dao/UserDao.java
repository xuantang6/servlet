/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDao {

    private DBDao dbDao;

    public UserDao() {
        this.dbDao = new DBDao();
    }

    public boolean registerUser(User user) {
        boolean success = false;
        try (Connection connection = dbDao.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO account (username, password, email, gender, contact_no, address, profile_pic, account_type, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, user.getUsername().trim());
            preparedStatement.setString(2, user.getPassword().trim());
            preparedStatement.setString(3, user.getEmail().trim());
            preparedStatement.setString(4, user.getGender().trim());
            preparedStatement.setString(5, user.getContact_no().trim());
            preparedStatement.setString(6, user.getAddress().trim());
            preparedStatement.setString(7, user.getProfile_pic().trim());
            preparedStatement.setString(8, user.getAccount_type().trim());
            preparedStatement.setBoolean(9, user.isStatus());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered successfully.");
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while registering user: " + e.getMessage());
        } finally {
            dbDao.closeConnection();
        }
        return success;
    }

    public User getUserByUsername(String username) {
        User user = null;
        try (Connection connection = dbDao.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Account WHERE username = ?")) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username").trim());
                user.setPassword(resultSet.getString("password").trim());
                user.setEmail(resultSet.getString("email").trim());
                user.setGender(resultSet.getString("gender").trim());
                user.setContact_no(resultSet.getString("contact_no").trim());
                user.setAddress(resultSet.getString("address").trim());
                user.setProfile_pic(resultSet.getString("profile_pic").trim());
                user.setAccount_type(resultSet.getString("account_type").trim());
                user.setStatus(resultSet.getBoolean("status"));

                System.out.println("User found: " + user.getEmail());
                return user;
            } else {
                System.out.println("User not found: " + username);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching user: " + e.getMessage());
        } finally {
            dbDao.closeConnection();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dbDao.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("gender"),
                        resultSet.getString("contact_no"),
                        resultSet.getString("address"),
                        resultSet.getString("profile_pic"),
                        resultSet.getString("account_type"),
                        resultSet.getBoolean("status")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());
        } finally {
            dbDao.closeConnection();
        }
        return users;
    }

    public boolean updateUser(User user) {
        boolean success = false;
        try (Connection connection = dbDao.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("UPDATE account SET password=?, email=?, gender=?, contact_no=?, address=?, profile_pic=?, account_type=?, status=? WHERE username=?")) {

            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getGender());
            preparedStatement.setString(4, user.getContact_no());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getProfile_pic());
            preparedStatement.setString(7, user.getAccount_type());
            preparedStatement.setBoolean(8, user.isStatus());
            preparedStatement.setString(9, user.getUsername());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        } finally {
            dbDao.closeConnection();
        }
        return success;
    }

    public boolean deleteUser(String username) {
        boolean success = false;
        try (Connection connection = dbDao.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE username=?")) {

            preparedStatement.setString(1, username);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        } finally {
            dbDao.closeConnection();
        }
        return success;
    }
}
