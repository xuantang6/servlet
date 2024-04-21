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
        doPost(request,response);
    }
    
    
            

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("signin-username");
        String password = request.getParameter("signin-password");
        LoginService loginService = new LoginService();
        
        User user = loginService.loginUser(username,password);
        
        if(user == null){
            //login fail
            request.setAttribute("tips", "<label style= 'color:red'>wrong password or username</label>");
            request.getRequestDispatcher("SignUpIn.jsp").forward(request,response);
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            response.sendRedirect("loginSuccess.jsp");
           
        }
    }
}
