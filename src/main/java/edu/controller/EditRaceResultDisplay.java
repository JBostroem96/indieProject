package edu.controller;


import edu.matc.entity.Role;
import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.ForwardEntry;
import edu.matc.util.GetEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class' purpose is to forward to the jsp
 */
@WebServlet(
        urlPatterns = {"/editRaceResultDisplay"}
)

public class EditRaceResultDisplay extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to forward to the jsp
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, null)) {
            return;
        }

        new ForwardEntry<>("/editRaceResult.jsp", req, resp, new GetEntry<TeamRace>().parseEntry(new GenericDao<>(TeamRace.class), req, log()), new GenericDao<>(Team.class).getAll());
    }
}

