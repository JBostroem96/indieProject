package edu.matc.util;

import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * This class' purpose is to get the entry
 * @param <T> the entity
 */
public class GetEntry<T> {

    /**
     * This is the constructor
     */
    public GetEntry() {


    }

    /**
     * This method's purpose is to make sure the id is valid and to get/parse the entry
     * @param dao the generic dao
     * @param req the request object
     * @param logger the logger
     * @return the entry - the parsed entry
     */
    public T parseEntry(GenericDao<T> dao, HttpServletRequest req, Logger logger) {

        T entry = null;
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

                entry = dao.getById(Integer.parseInt(id));

            } catch (Exception e) {

                logger.error("Something went wrong!", e);
            }
        }
        return entry;
    }
}

