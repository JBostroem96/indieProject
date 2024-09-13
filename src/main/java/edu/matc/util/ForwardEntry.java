package edu.matc.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This class' purpose is to forward the entry to the page
 * @param <T> the first entity
 * @param <U> the second entity
 */
public class ForwardEntry<T, U>  {

    /**
     * This method's purpose is to forward to the page
     * @param url the url to forward to
     * @param req the request object
     * @param resp the response object
     * @param entry the entry passed over
     * @param displayEntries the list of entries to be displayed
     * @throws ServletException if there is an issue in the servlet
     * @throws IOException if the IO operations fail
     */
    public ForwardEntry(String url, HttpServletRequest req, HttpServletResponse resp, T entry, List<U> displayEntries) throws ServletException, IOException {

        if (entry != null) {

            req.setAttribute("entry", entry);
        }
        if (displayEntries != null) {

            req.setAttribute("displayEntries", displayEntries);
        }
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
