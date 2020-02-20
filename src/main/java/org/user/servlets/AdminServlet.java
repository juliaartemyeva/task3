package org.user.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.user.model.User;
import org.user.service.UserService;

import org.user.service.UserServiceImpl;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List <User> listUser = userService.selectAllUsers();
        req.setAttribute("listUser", listUser);
        req.getRequestDispatcher("/admin-page.jsp").forward(req, resp);
    }
}
