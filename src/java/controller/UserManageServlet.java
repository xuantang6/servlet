/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.User;
import services.LoginService;
import services.UserService;

@MultipartConfig(location = "D:/NetBeansProject/PracticalQuestion/ASGM/web/profile_picture/")
public class UserManageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String formAction = request.getServletPath();
        System.out.println(formAction);
        UserService userService = new UserService();
        switch (formAction) {
            case "/addUserServlet":
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String gender = request.getParameter("gender");
                String contact = request.getParameter("contact");
                String address = request.getParameter("address");
                String userType = request.getParameter("userType");
                String accStatus = request.getParameter("accStatus");
                String password = request.getParameter("password");
                String confPassword = request.getParameter("confPassword");

                //get profile pic and save
                //get upload pic data such as name, size ... 
                Part profile_pic = request.getPart("profile_pic");
                //get full file name in oder to get file extention
                String header = profile_pic.getHeader("Content-Disposition");
                //get file extention
                String ext = header.substring(header.lastIndexOf("."), header.lastIndexOf("\""));
                String fileName = UUID.randomUUID().toString() + ext;
                //get profile_picture dir
                String dir = getServletContext().getRealPath("/") + "profile_picture";
//                System.out.println(dir);
                String savePath = dir + "\\" + fileName;
//                System.out.println(savePath);
                //savePath = savePath.replace("\\build", "").trim();
                //save
                profile_pic.write(fileName);

                LoginService loginService = new LoginService();
                boolean isPsMatched = loginService.isPasswordMatched(password, confPassword);
                if (isPsMatched == false) {
                    request.setAttribute("tips", "<label style= 'color:red'>Password not matched</label>");
                    request.getRequestDispatcher("registerResult.jsp").forward(request, response);
                } else if (loginService.validatePassword(password) == false) {
                    request.setAttribute("tips", "<label style= 'color:red'>Password not fulfill requirement</label>");
                    request.getRequestDispatcher("registerResult.jsp").forward(request, response);
                } else {
                    User user = new User(username, password, email, gender, contact, address, "profile_picture/" + fileName, userType, Boolean.parseBoolean(accStatus));
                    boolean b = userService.addUser(user);

                    String tips = b ? "<div id=\"alertContainer\" class=\"alert alert-success alert-dismissible fade show d-flex align-items-center justify-content-center \" role=\"alert\">\n"
                            + "        <strong>New users have been added!&nbsp;</strong> <img height=\"20px\" src=\"img/correct.png\" alt=\"Correct\">\n"
                            + "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n"
                            + "    </div>" : "<label style= 'color:red'>Somethings went Wrong</label>";
                    request.setAttribute("tips", tips);
                    request.getRequestDispatcher("listUserServlet").forward(request, response);
                }

                break;

            case "/listUserServlet":
                List<User> userList = userService.listUser();
                request.setAttribute("userList", userList);
                request.getRequestDispatcher("userManagement.jsp").forward(request, response);

//                HttpSession loginSession = request.getSession();
//                User loginUser = (User) loginSession.getAttribute("user");
//                if(loginUser != null && "staff".equalsIgnoreCase(loginUser.getAccount_type())){
//                    HttpSession session = request.getSession();
//                    String accType = "staff";
//                    session.setAttribute("userType", accType);
//                    response.sendRedirect("dashboard.jsp");
//                }else{
//                    System.out.println("hey" + loginUser);
//
//                    List<User> userList = userService.listUser();
//                    System.out.println("hey list");
//                    request.setAttribute("userList", userList);
//                    System.out.println(userList);
//                    request.getRequestDispatcher("userManagement.jsp").forward(request, response); 
//                }
//                
//                
                break;

            case "/delUserServlet":
                HttpSession session = request.getSession();
                User staff = (User) session.getAttribute("user");
                
                if (staff != null && "staff".equalsIgnoreCase(staff.getAccount_type())) {

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();

                    String jsonUser = gson.toJson(staff);
                   
                    PrintWriter out = response.getWriter();
                    out.print(jsonUser);
                    out.flush();
                } else {
                    
                    String uname = request.getParameter("uname");
                    boolean b = userService.deleteUser(uname);
                    String tips = b ? "<div id=\"alertContainer\" class=\"alert alert-success alert-dismissible fade show d-flex align-items-center justify-content-center \" role=\"alert\">\n"
                            + "        <strong>" + uname + "</strong>&nbsp; has been deleted!&nbsp; <img height=\"20px\" src=\"img/correct.png\" alt=\"Correct\">\n"
                            + "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n"
                            + "    </div>" : "<label style= 'color:red'>Somethings went Wrong</label>";
//                    request.setAttribute("tips", tips);
//                    request.getRequestDispatcher("listUserServlet").forward(request, response);
                    request.getSession().setAttribute("tips", tips);
                    response.sendRedirect("listUserServlet");
                    System.out.println("staff");
                    //use session to pass error msg else cant redirect
                }

                break;

            case "/queryUserServlet":
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                String uName = request.getParameter("uname");

                User user = userService.getUserByUsername(uName);
                Gson gson = new Gson();

                String jsonUser = gson.toJson(user);
                

                PrintWriter out = response.getWriter();
                out.print(jsonUser);
                out.flush();

                System.out.println("Received username: " + uName);

                break;

            case "/updateUserServlet":
                HttpSession s = request.getSession();
                User st = (User) s.getAttribute("user");
                
                if(st != null && "staff".equalsIgnoreCase(st.getAccount_type())){
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gSon = new Gson();

                    String jsUser = gSon.toJson(st);
                    System.out.println(st);

                    PrintWriter outt = response.getWriter();
                    outt.print(jsUser);
                    outt.flush();
                }else{
                    String usernameUp = request.getParameter("username");
                    String emailUp = request.getParameter("email");
                    String genderUp = request.getParameter("gender");
                    String contactUp = request.getParameter("contact");
                    String addressUp = request.getParameter("address");
                    String userTypeUp = request.getParameter("userType");
                    String accStatusUp = request.getParameter("accStatus");
                    String passwordUp = request.getParameter("password");
                    String imgPath = request.getParameter("imgPath");

                    User userUp = new User(usernameUp, passwordUp, emailUp, genderUp, contactUp, addressUp, imgPath, userTypeUp, Boolean.parseBoolean(accStatusUp));
                    boolean bool = userService.updateUser(userUp);

                    String tips = bool ? "<div id=\"alertContainer\" class=\"alert alert-success alert-dismissible fade show d-flex align-items-center justify-content-center \" role=\"alert\">\n"
                            + "        <strong>User information has been updated&nbsp;</strong> <img height=\"20px\" src=\"img/correct.png\" alt=\"Correct\">\n"
                            + "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n"
                            + "    </div>" : "<label style= 'color:red'>Somethings went Wrong</label>";
//                request.setAttribute("tips", tips);
//                request.getRequestDispatcher("listUserServlet").forward(request, response);
                    request.getSession().setAttribute("tips", tips);
                    response.sendRedirect("listUserServlet");

                }
                
                break;

            case "/checkUsernameServlet":
                String signUpUname = request.getParameter("signup-username");

                UserService uService = new UserService();
                User checkUser = uService.getUserByUsername(signUpUname);

                if (checkUser == null) {
                    response.getWriter().write("Username exists");
                } else {
                    response.getWriter().write("Username does not exist");
                }
                break;

            default:
                System.out.println("hi");
        }

    }

}
