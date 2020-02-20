package org.user.servlets;

import org.user.model.User;
import org.user.service.UserService;
import org.user.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/update")
public class AdminUpdateUserServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String country = req.getParameter("country");
        String role = req.getParameter("role");
        User user = new User(id, login, password, country, role);
        userService.updateUser(user);
        resp.sendRedirect("/admin");
    }
}

