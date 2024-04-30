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

        User user = new User(username, password, email, "M", "", "", "", "Customer", true);
        UserService userService = new UserService();
        boolean b = userService.addUser(user);

        String tips = b ? "<label style= 'color:green'>new user added</label>" : "<label style= 'color:red'>Somethings went Wrong</label>";
        request.setAttribute("tips", tips);
        request.getRequestDispatcher("registerResult.jsp").forward(request, response);
    }

}
