package edu.controller;

import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.restService.TLSEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/reportResultById"}
)
/**
 * This class' purpose is to submit the report and send the email
 */
public class ReportResultById extends HttpServlet {

    /**
     * This method's purpose is to submit the report and send the email
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(TeamRace.class);
        TeamRace resultToReport = (TeamRace)dao.getById(Integer.parseInt(req.getParameter("id")));
        String description = req.getParameter("teamTextArea");
        String subject = req.getParameter("subject") + " regarding " + resultToReport.getTeam().getName() + " in race " + resultToReport.getRace().getName();

        TLSEmail mail = new TLSEmail();

        mail.simpleEmailWithTLS(subject, description);

        req.setAttribute("resultReported", resultToReport.getTeam().getName());
        req.setAttribute("result", resultToReport);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/reportResult.jsp");
        dispatcher.forward(req, resp);
    }
}
