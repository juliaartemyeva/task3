package org.user.servlets;

import org.user.model.User;
import org.user.service.UserService;
import org.user.service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/insert")
public class AdminInsertUserServlet extends HttpServlet {
    private UserService userUserService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String country = req.getParameter("country");
        String role = req.getParameter("role");
        User newUser = new User(login, password, country, role);
        userUserService.insertUser(newUser);
        resp.sendRedirect("/admin");

    }
}
