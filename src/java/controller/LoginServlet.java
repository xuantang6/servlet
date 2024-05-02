/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import model.User;
import services.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String formAction = request.getServletPath();
        System.out.println(formAction);
        

        if (formAction != null && formAction.equalsIgnoreCase("/LoginServlet")) {

            String username = request.getParameter("signin-username");
            String password = request.getParameter("signin-password");
            LoginService loginService = new LoginService();
            User user = loginService.loginUser(username, password);

            if (user == null) {
                //login fail
                request.setAttribute("tips", "<label id=\"errorMessage\" style= 'color:red'>Wrong username or password, please try again</label>");
                request.getRequestDispatcher("signUpIn.jsp").forward(request, response);
            } else {
                
                if(user.getAccount_type().equalsIgnoreCase("admin") || user.getAccount_type().equalsIgnoreCase("staff") ){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("userManagement.jsp");
                }else{
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("loginSuccess.jsp");
                }
                
                

            }
            
        }else if(formAction != null && formAction.equalsIgnoreCase("/ResetPassword")){
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String newPassword = request.getParameter("new_password");
            String confPassword = request.getParameter("confirm_password");
            
            LoginService loginService = new LoginService();
            boolean bool = loginService.resetPassword(username, email, newPassword, confPassword);
            boolean psMatched = loginService.isPasswordMatched(newPassword, confPassword);

            System.out.println("resetps");
            System.out.println("ps match = " + psMatched);
            System.out.println(bool);
            
            String tips;
            if (bool == true) {
                tips ="<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n"
                        + "  <strong>Congratulation!</strong> You password has been reset, sign in now!\n"
                        + "  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n"
                        + "</div>";
                
                request.setAttribute("psResetInfo", tips);
                request.getRequestDispatcher("signUpIn.jsp").forward(request, response);
            } else if(bool == false && psMatched == false){
                tips = "<label style= 'color:red'>Password not matched</label>";
                request.setAttribute("psErrorInfo", tips);
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
               
            }else if(bool == false && psMatched == true){
                tips = "<label style= 'color:red'>User does not exist, please try again.</label>";
                request.setAttribute("psErrorInfo", tips);
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
                
            }else{
                System.out.println("something wrong");
            }
            
            
        }

    }
}

