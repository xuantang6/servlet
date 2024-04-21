/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.UserDao;
import model.User;

/**
 *
 * @author USER
 */
public class LoginService {

    public User loginUser(String username, String password) {
        User user = null;
        UserDao userDAO = new UserDao();

        user = userDAO.getUserByUsername(username);
        
        if (user == null) {
            System.out.println("User not exist");
            return null;
            
        } else if (user.getPassword() .equals(password)) {
            System.out.println("yes");
            return user;
            
        }else{
            System.out.println("what");
            return null;
        }
    }
}
