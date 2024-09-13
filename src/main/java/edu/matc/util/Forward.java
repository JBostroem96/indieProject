package edu.matc.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Forward<T, U>  {

    public Forward(String url, HttpServletRequest req, HttpServletResponse resp, T entry, List<U> displayEntries) throws ServletException, IOException {

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
