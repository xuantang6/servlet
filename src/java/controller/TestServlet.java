import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import model.User;
import services.UserService;
                      

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/plain");

        // 获取输出流
        PrintWriter out = response.getWriter();

        // 向客户端发送数据
        out.println("Response from TestServlet");

        // 关闭输出流
        out.close();

    }
}
