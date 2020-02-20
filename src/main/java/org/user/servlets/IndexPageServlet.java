package org.user.servlets;

import org.user.model.User;
import org.user.service.UserService;
import org.user.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class IndexPageServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        if (userService.userIsExist(login, password)) {
            String role = (String) session.getAttribute("role");
            if (role.equals("admin")) {
                req.getRequestDispatcher("/admin-page.jsp").forward(req, resp);
            } else if (role.equals("user")) {
                req.getRequestDispatcher("/user-page.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("status", "There are no such User in system! Please try again!");
            req.getRequestDispatcher("/index-page.jsp").forward(req, resp);
        }
    }
}