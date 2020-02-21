package org.user.filters;

import org.user.model.User;

import javax.servlet.*;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/login")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        session.setAttribute("role", role);
        if ("user".equals(role)) {
            resp.sendRedirect("/user");
        } else if ("admin".equals(role)) {
            resp.sendRedirect("/admin");
        } else {
            req.getSession().removeAttribute("user");
            req.getSession().removeAttribute("role");
            resp.sendRedirect("/");
        }
    }
}
