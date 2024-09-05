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
     * This method's purpose is to authorize the user
     * @param resp the response object
     * @param req the request object
     * @param admin if it requires admin permission
     * @param user if it requires user permission
     * @return whether authorization failed or succeeded
     */
    default boolean authorize(HttpServletResponse resp, HttpServletRequest req, Role admin, Role user) {

        Logger logger = log();

        User userEntry = (User) req.getSession().getAttribute("user");

        if (user != null) {

            if (userEntry == null || !userEntry.getRole().equals(admin.toString()) && !userEntry.getRole().equals(user.toString())) {

                try {

                    resp.sendRedirect(req.getContextPath() + "/");

                } catch (Exception e) {
                    logger.error("There was an authorization issue", e);
                }

                return false;
            }

        } else {

            if (userEntry == null || !userEntry.getRole().equals(admin.toString())) {

                try {

                    resp.sendRedirect(req.getContextPath() + "/");

                } catch (Exception e) {
                    logger.error("There was an authorization issue", e);
                }

                return false;
            }

        }
        return true;
    }
}
