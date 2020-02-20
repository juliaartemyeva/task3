package org.user.filters;

import javax.servlet.*;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/user")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String role = (String) req.getSession().getAttribute("role");
        HttpSession session = req.getSession();
        if (nonNull(session) && (role.equals("admin") || role.equals("user"))) {
            req.getRequestDispatcher("/user-page.jsp");
            chain.doFilter(req, resp);
        } else if ("user".equals(role) | "admin".equals(role)) {
            chain.doFilter(req, resp);
        } else {
            req.getSession().removeAttribute("login");
            req.getSession().removeAttribute("password");
            resp.sendRedirect("/");
        }
    }
}
