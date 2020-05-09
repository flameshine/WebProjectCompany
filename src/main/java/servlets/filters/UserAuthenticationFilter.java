package servlets.filters;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import org.apache.log4j.Logger;

public class UserAuthenticationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(UserAuthenticationFilter.class);

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {

            final HttpServletRequest req = (HttpServletRequest) servletRequest;
            final HttpServletResponse resp = (HttpServletResponse) servletResponse;

            final HttpSession session = req.getSession();

            if (session == null || session.getAttribute("username") == null || session.getAttribute("password") == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
                logger.info("Filter rejected user login attempt without authentication...");
            } else {
                filterChain.doFilter(servletRequest, resp);
                logger.info("Filter confirmed user login attempt...");
            }
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}