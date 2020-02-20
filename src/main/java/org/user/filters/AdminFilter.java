package org.user.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String role = (String) req.getSession().getAttribute("role");
        if ("admin".equals(role)) {
            chain.doFilter(req, resp);
        } else {
            req.getSession().removeAttribute("login");
            req.getSession().removeAttribute("password");
            req.getRequestDispatcher("/index-page.jsp").forward(req, resp);
        }
    }
}
