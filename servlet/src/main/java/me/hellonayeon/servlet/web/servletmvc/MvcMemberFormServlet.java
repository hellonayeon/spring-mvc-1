package me.hellonayeon.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath); // Controller 에서 View 로 이동 시 사용
        requestDispatcher.forward(request, response); // forward(): 다른 Servlet 이나 JSP 로 이동할 수 있는 기능, 서버 내부에서 다시 호출이 발생
    }
}
