package edu.controller;

import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/reportResultDisplay"}
)

/**
 * This class' purpose is to forward to the corresponding JSP
 */
public class ReportResultDisplay extends HttpServlet {

    /**
     * This method's purpose is to forward to the jsp
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(TeamRace.class);
        TeamRace resultToReport = (TeamRace)dao.getById(Integer.parseInt(req.getParameter("id")));

        req.setAttribute("result", resultToReport);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/reportResult.jsp");
        dispatcher.forward(req, resp);
    }
}
