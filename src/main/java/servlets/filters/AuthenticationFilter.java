package servlets.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {

            final HttpServletRequest req = (HttpServletRequest) servletRequest;
            final HttpServletResponse resp = (HttpServletResponse) servletResponse;

            final HttpSession session = req.getSession();

            if (session == null || session.getAttribute("username") == null)
                resp.sendRedirect(req.getContextPath() + "/login");
            else {
                filterChain.doFilter(servletRequest, resp);
                resp.sendRedirect(req.getContextPath() + "/distributor");
            }
        }

    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}