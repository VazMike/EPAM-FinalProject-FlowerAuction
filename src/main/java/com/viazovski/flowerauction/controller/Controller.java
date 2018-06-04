package com.viazovski.flowerauction.controller;

import com.viazovski.flowerauction.command.Command;
import com.viazovski.flowerauction.connection.ConnectionPool;
import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.viazovski.flowerauction.controller.MappingUtil.unfoldExceptionStackTrace;

/**
 * The application is built using single-servlet model. {@code Controller} is the servlet.
 * {@code Controller} relays requests to a specific command in the command layer.
 */
@WebServlet(name = "Controller",
        urlPatterns = {"/welcome", "", "/sign-in", "/sign-up",
        "/sign-out", "/profile", "/edit-profile", "/settings", "/manage-auctions", "/change-password",
        "/manage-requests", "/manage-users", "/accept-buyer", "/reject-buyer",
        "/accept-flower", "/reject-flower", "/remove-auction", "/remove-user",
        "/request", "/make-auction-request", "/make-flower-request",
        "/add-auction", "/edit-auction", "/find-available-flowers",
        "/business-profile", "/edit-flower", "/remove-flower", "/remove-credit-card",
        "/add-flower", "/add-credit-card", "/running-auction", "/change-locale"}
        )
public class Controller extends HttpServlet {

    private static Logger logger = LogManager.getLogger();

    @Override
    public void init() {
    }

    /**
     * Forwards the JSP page corresponding to the request.
     *
     * @param req   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet.
     * @param resp  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client.
     * @throws ServletException
     *              if {@link javax.servlet.RequestDispatcher#forward(ServletRequest, ServletResponse)} does.
     * @throws IOException
     *              if either {@link javax.servlet.RequestDispatcher#forward(ServletRequest, ServletResponse)}
     *              or {@link MappingUtil#getUri(HttpServletRequest)} does.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = MappingUtil.getUri(req);
        String page = MappingUtil.mapToJsp(uri);
        logger.debug("GET request uri: " + uri);
        req.getRequestDispatcher(page).forward(req, resp);
    }

    /**
     * All POST-methods are triggered via AJAX call. Corresponding command gets executed.
     *
     * @param req   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet.
     * @param resp  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client.
     * @throws IOException if any of {@link ServletResponse#getWriter()}
     *              {@link HttpServletResponse#sendError(int, String)}
     *              {@link MappingUtil#getUri(HttpServletRequest)} does.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = MappingUtil.getUri(req);
        Command command = MappingUtil.mapToCommand(uri);
        logger.debug("POST request uri: " + uri);
        try {
            String json = command.execute(req);
            logger.debug("JSON retrieved: " + json);
            resp.getWriter().write(json);
        } catch (CommandException e) {
            resp.sendError(500, unfoldExceptionStackTrace(e));
        }
    }

    /**
     * Releases all database resources.
     */
    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().drain();
        } catch (ConnectionException e) {
            String msg = "Unable to deregister MySQL driver";
            logger.fatal(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
