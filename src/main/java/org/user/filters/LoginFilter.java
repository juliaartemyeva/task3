package org.user.filters;

        import org.user.service.UserService;
        import org.user.service.UserServiceImpl;

        import javax.servlet.*;

        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;


@WebFilter("/login")
public class LoginFilter implements Filter {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (userService.userIsExist(login, password)) {
            String role = userService.getRoleByLoginPassword(login, password);
            session.setAttribute("role", role);
            if ("user".equals(role)) {
                resp.sendRedirect("/user");
            } else if ("admin".equals(role)) {
                resp.sendRedirect("/admin");
            } else {
                req.getSession().removeAttribute("login");
                req.getSession().removeAttribute("password");
                resp.sendRedirect("/");
            }
        }
    }
}
