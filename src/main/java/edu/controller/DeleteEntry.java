package edu.controller;


import edu.matc.persistence.GenericDao;
import edu.matc.util.UseLogger;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 * This class' purpose is to delete the entry
 * @param <T> the entity
 */
public class DeleteEntry<T> implements UseLogger {


    /**
     * This method's purpose is to delete the entry
     * @param dao the generic dao
     * @param req the request object
     */
    public DeleteEntry(GenericDao<T> dao, HttpServletRequest req) {

        final Logger logger = log();

        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

                T entry = dao.getById(Integer.parseInt(id));
                dao.delete(entry);
                req.setAttribute("deletedEntry", entry);

            } catch (Exception e) {

                req.setAttribute("e", e);
                logger.error("Something went wrong!", e);
            }
        }
    }
}
