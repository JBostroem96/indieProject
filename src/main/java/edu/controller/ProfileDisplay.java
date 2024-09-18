package edu.controller;

import edu.matc.entity.Role;
import edu.matc.entity.TeamRace;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.ForwardEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        urlPatterns = {"/profile"}
)

/**
 * This class' purpose is to display the user profile along with their submitted results
 */
public class ProfileDisplay extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to forward to the jsp
     * @param req  the request object that we forward
     * @param resp the response object that we forward
     * @throws ServletException if an error occurs with the Servlet
     * @throws IOException      if an error occurs with the IO operations
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, Role.USER)) {
            return;
        }

        User user = (User) req.getSession().getAttribute("user");

        //Get the submitted results by the user, and then forward to the page
        new ForwardEntry<>("/profile.jsp", req, resp, user, getEntries(user));
    }


    /**
     * This method's purpose is to get all the results associated with the user
     * @param user the user logged in
     * @return the list of results
     */
    public List<TeamRace> getEntries(User user) {

        List<TeamRace> entries = new ArrayList<>();

        for (TeamRace entry : new GenericDao<>(TeamRace.class).getAll()) {

            if (entry.getUser().getId() == user.getId()) {

                entries.add(entry);
            }
        }

        return entries;
    }
}
