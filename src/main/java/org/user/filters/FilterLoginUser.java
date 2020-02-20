package org.user.filters;

import org.user.service.UserServiceImpl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import static java.util.Objects.nonNull;

@WebFilter("/")
public class FilterLoginUser implements Filter {
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            String role = (String) session.getAttribute("role");
            moveToMenu(req, res, role);
            if (role.equals("admin")) {
                req.getRequestDispatcher("admin-page.jsp");
            } else {
                req.getRequestDispatcher("user-page.jsp");
            }
        } else if (userService.userIsExist(login, password)) {
            final String role = userService.getRoleByLoginPassword(login, password);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            moveToMenu(req, res, role);
        } else {
            moveToMenu(req, res, "Unknown");
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
        } else {
            req.getRequestDispatcher("index-page.jsp").forward(req, res);
        }
    }
}