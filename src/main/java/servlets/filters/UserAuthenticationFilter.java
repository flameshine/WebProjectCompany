package servlets.filters;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserAuthenticationFilter implements Filter {

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

            if (session == null || session.getAttribute("username") == null || session.getAttribute("password") == null)
                resp.sendRedirect(req.getContextPath() + "/login");
            else
                filterChain.doFilter(servletRequest, resp);
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}