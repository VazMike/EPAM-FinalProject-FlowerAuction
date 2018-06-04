package com.viazovski.flowerauction.controller;

import com.viazovski.flowerauction.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {@code AdminAccessFilter} specifies requests exclusively
 * available to clients with {@link Role#ADMIN} role.
 * If a request is sent by a client with other role he
 * gets redirected to other page.
 */
@WebFilter(filterName = "AdminAccess",
        urlPatterns = {
                "/accept-buyer", "/accept-flower", "/add-auction",
                "/edit-auction",
                "/manage-auctions", "/manage-requests", "/manage-users",
                "/reject-buyer", "/reject-flower",
                "/remove-auction", "/remove-user"
})
public class AdminAccessFilter implements Filter {

    private static Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        var session = req.getSession(false);
        if (session != null) {
            var role = session.getAttribute("role");
            if (role != null && "ADMIN".equals(role.toString())) {
                logger.debug("doFilter");
                chain.doFilter(request, response);
                return;
            }
        }
        logger.debug("Redirect");
        resp.sendRedirect("welcome");
    }

    @Override
    public void destroy() {
    }
}
