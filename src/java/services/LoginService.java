/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.LoginDao;
import dao.UserDao;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        } else if (user.getPassword().equals(password)) {
            System.out.println("yes");
            return user;

        } else {
            System.out.println("what");
            return null;
        }
    }
    
    public boolean resetPassword(String username, String email, String newPassword, String confPassword){
        User user = null;
        UserDao userDAO = new UserDao();

        user = userDAO.getUserByUsername(username);
        LoginDao loginDAO = new LoginDao();
        
        boolean bool = isPasswordMatched(newPassword,confPassword);
        if(user.getEmail().equalsIgnoreCase(email)){
            
            if(bool == true){
                //pass matched
                return loginDAO.storePassword(username, newPassword);
            }else{
                //pass not matched
                return false;
            }
            
            
        }else{
            //email not matched with username
            return false;
        }
        }
    
    public boolean isPasswordMatched(String newPassword, String confPassword){
        
        return newPassword.equalsIgnoreCase(confPassword);
    }
    
    public boolean validatePassword(String password) {
        
        if (password.length() < 8) {
            return false;
        }

        
        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Pattern specialCharacterPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher upperCaseMatcher = upperCasePattern.matcher(password);
        Matcher lowerCaseMatcher = lowerCasePattern.matcher(password);
        Matcher specialCharacterMatcher = specialCharacterPattern.matcher(password);

        return upperCaseMatcher.find() && lowerCaseMatcher.find() && specialCharacterMatcher.find();
    }
    }
    
    

