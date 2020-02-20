package org.user.servlets;

import org.user.service.UserService;
import org.user.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/")
public class IndexPageServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (nonNull(session) && nonNull(session.getAttribute("role"))) {
            String role = (String) session.getAttribute("role");
            moveToMenu(req, resp, role);
            if (role.equals("admin")) {
                req.getRequestDispatcher("/admin-page.jsp");
            } else {
                req.getRequestDispatcher("/user-page.jsp");
            }
        } else {
            getServletContext().getRequestDispatcher("/index-page.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        if (userService.userIsExist(login, password)) {
            session.setAttribute("login", login);
            String role = userService.getRoleByLoginPassword(login, password);
            if (role.equals("admin")) {
                session.setAttribute("role", "admin");
                resp.sendRedirect("/admin");
            } else if (role.equals("user")) {
                session.setAttribute("role", "user");
                resp.sendRedirect("/user");
            }
        } else {
            req.getRequestDispatcher("/index-page.jsp").forward(req, resp);
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {
        if (role.equals("admin")) {
            res.sendRedirect("/admin");
        } else if (role.equals("user")) {
            res.sendRedirect("/user");
        }
    }
}