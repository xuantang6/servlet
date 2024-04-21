/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.UserDao;
import java.util.List;
import model.User;

/**
 *
 * @author USER
 */
public class UserService {
    private UserDao userDAO = new UserDao();
    
    public List<User> listUser(){
        List<User> userList = userDAO.getAllUsers();
        return userList; 
    }
    
    public User getUserByUserame(String username){
        User user = userDAO.getUserByUsername(username);
        return user;
    }
    
    public boolean addUser(User user){
        boolean bool = userDAO.registerUser(user);
        System.out.println(user);
        System.out.println("hellouser");
        return bool;
    }
    
    public boolean deleteUser(String username){
        boolean bool = userDAO.deleteUser(username);
        return bool;
    }

    public boolean updateUser(User user){
        boolean bool = userDAO.updateUser(user);
        return bool;
    }
}
