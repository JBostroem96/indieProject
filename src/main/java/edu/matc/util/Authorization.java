package edu.matc.util;

import edu.matc.entity.Role;
import edu.matc.entity.User;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface's purpose is to authorize the user
 */
public interface Authorization extends UseLogger {

    /**
     * Authorizes the user based on their role.
     *
     * @param resp the response object
     * @param req the request object
     * @param requiresAdmin if the action requires admin permission
     * @param requiresUser if the action requires user permission
     * @return true if authorized, false otherwise
     */
    default boolean authorize(HttpServletResponse resp, HttpServletRequest req, Role requiresAdmin, Role requiresUser) {

        Logger logger = log();
        User userEntry = (User) req.getSession().getAttribute("user");

        if (userEntry == null || !isRoleAuthorized(userEntry, requiresAdmin, requiresUser)) {
            logAndRedirect(resp, req, logger);
            return false;
        }

        return true;
    }

    /**
     * Checks if the user's role matches the required roles.
     */
    default boolean isRoleAuthorized(User userEntry, Role requiresAdmin, Role requiresUser) {
        return userEntry.getRole().equals(requiresAdmin) || userEntry.getRole().equals(requiresUser);
    }

    /**
     * Logs the authorization failure and redirects to home.
     */
    default void logAndRedirect(HttpServletResponse resp, HttpServletRequest req, Logger logger) {
        try {
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (Exception e) {
            logger.error("There was an authorization issue", e);
        }
    }
}
