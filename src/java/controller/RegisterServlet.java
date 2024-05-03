/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import services.UserService;

/**
 *
 * @author USER
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("signup-username");
        String email = request.getParameter("email");
        String password = request.getParameter("signup-password");

        if (username != null && email != null && password != null) {
            User user = new User(username, password, email, "M", "", "", "", "Customer", true);
            UserService userService = new UserService();
            boolean b = userService.addUser(user);

            String tips = b ? "registerSuccess" : "registerFail";
            
            HttpSession session = request.getSession();
            session.setAttribute("registerStatus", tips);
            response.sendRedirect("signUpIn.jsp");

        } else {

        }

    }

}
