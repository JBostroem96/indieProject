package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/searchTeam"}
)
public class SearchTeam extends HttpServlet {

    /**
     * This method's purpose is to search for a race or all races
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Team.class);

        if (req.getParameter("submit").equals("search")) {

            if (req.getParameter("searchType").equals("division")) {

                req.setAttribute("teams", dao.findByPropertyEqual("division", req.getParameter("searchTerm")));

            } else {

                req.setAttribute("teams", dao.findByPropertyEqual("name", req.getParameter("searchTerm")));
            }

        } else  {

            req.setAttribute("teams", dao.getAll());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchTeamResults.jsp");
        dispatcher.forward(req, resp);
    }
}
