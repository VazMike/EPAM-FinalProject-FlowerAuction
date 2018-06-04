package com.viazovski.flowerauction.controller;

import com.viazovski.flowerauction.command.Command;
import com.viazovski.flowerauction.command.CommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code MappingUtil} is util class containing helper methods
 * to relay task of generating response to some other class in
 * command layer.
 */
@RequestMethods(
        gets = {
                "", "business-profile",
                "manage-auctions", "manage-users", "manage-requests",
                "profile", "request", "settings", "welcome"
        },
        posts = { "", "accept-buyer", "accept-flower", "add-auction",
                "add-credit-card", "add-flower", "business-profile",
                "change-locale", "change-password", "edit-auction",
                "edit-flower", "edit-profile", "find-available-flowers",
                "make-auction-request", "make-flower-request",
                "manage-auctions", "manage-requests", "manage-users",
                "profile", "reject-buyer", "reject-flower",
                "remove-auction", "remove-credit-card", "remove-flower",
                "remove-user", "request", "sign-in", "sign-out", "sign-up"
        })
class MappingUtil {

    private static Logger logger = LogManager.getLogger();

    private static Map<String, String> mappingUriJsp = new HashMap<>();

    static {
        mappingUriJsp.put("", "pages/guest/welcome.jsp");
        mappingUriJsp.put("business-profile", "pages/user/business-profile.jsp");
        mappingUriJsp.put("manage-auctions", "pages/admin/manage-auctions.jsp");
        mappingUriJsp.put("manage-requests", "pages/admin/manage-requests.jsp");
        mappingUriJsp.put("manage-users", "pages/admin/manage-users.jsp");
        mappingUriJsp.put("profile", "pages/user/profile.jsp");
        mappingUriJsp.put("request", "pages/user/request.jsp");
        mappingUriJsp.put("settings", "pages/user/settings.jsp");
        mappingUriJsp.put("welcome", "pages/guest/welcome.jsp");
    }

    /**
     * Extracts URI from URL supplied by client's request.
     *
     * @param req the {@code HttpServletRequest} containing user input
     *          parameters and session attributes.
     * @return {@code String} URI.
     * @throws MalformedURLException if invalid URL was passed by a client.
     */
    static String getUri(HttpServletRequest req) throws MalformedURLException {
        return new URL(req.getRequestURL().toString())
                .getPath().replaceFirst("/", "");
    }

    static Command mapToCommand(String uri) {
        String commandType = !uri.isEmpty() ? toEnumFormat(uri) : "EMPTY";
        logger.debug("Command: " + commandType);
        return CommandType.valueOf(commandType).getCommand();
    }

    static String mapToJsp(String uri) {
        return mappingUriJsp.get(uri);
    }

    /**
     * Builds message containing all exception messages
     * in stack trace.
     *
     * @param e caught exception
     * @return Stack error message.
     */
    static String unfoldExceptionStackTrace(Throwable e) {
        StringBuilder message = new StringBuilder();
        while (e != null) {
            message.append(Arrays.toString(e.getStackTrace())).append("\n");
            e = e.getCause();
        }
        return message.toString();
    }

    /**
     * Transforms name into a legal enum identifier.
     * By Java convention enum values must be in uppercase and words separated
     * by underscores. Also dashes are illegal to use in identifiers.
     *
     *
     * @param word {@code String} lowercase letters with dashes.
     * @return enum-formatted {@code String}.
     */
    private static String toEnumFormat(String word) {
        return word.trim().replace('-', '_').toUpperCase();
    }
}
