package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;
import edu.matc.util.ForwardEntry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * This class' purpose is to set the attribute depending on
 * what option the user selects
 */
@WebServlet(
        urlPatterns = {"/search"}
)

public class Search extends HttpServlet {

    /**
     * This method's purpose is to search for races or teams
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<Race> raceDao = new GenericDao<>(Race.class);
        GenericDao<Team> teamDao = new GenericDao<>(Team.class);

        if (req.getParameter("submit").equals("search")) {

            if (req.getParameter("searchType").equals("length")) {

                req.setAttribute("races", raceDao.findByPropertyEqual("length", req.getParameter("searchTerm")));

            } else if (req.getParameter("searchType").equals("division")) {

                req.setAttribute("teams", teamDao.findByPropertyEqual("division", req.getParameter("searchTerm")));

            } else if (req.getParameter("searchType").equals("raceName")) {

                req.setAttribute("races", raceDao.findByPropertyEqual("name", req.getParameter("searchTerm")));

            } else if (req.getParameter("searchType").equals("teamName")) {

                req.setAttribute("teams", teamDao.findByPropertyEqual("name", req.getParameter("searchTerm")));
            }

        } else if (req.getParameter("submit").equals("viewAllRaces"))  {

            req.setAttribute("races", raceDao.getAll());

        } else {

            req.setAttribute("teams", teamDao.getAll());
        }

        new ForwardEntry<>("/searchResults.jsp", req, resp, null, null);
    }
}
