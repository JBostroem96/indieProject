package edu.controller;

import edu.matc.entity.Role;
import edu.matc.entity.TeamRace;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.ForwardEntry;
import edu.matc.util.GetEntry;
import edu.restService.TLSEmail;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/reportResult"}
)
/**
 * This class' purpose is to submit the report and send the email
 */
public class ReportResult extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to submit the report and send the email
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, Role.USER)) {
            return;
        }

        final Logger logger = log();

        String subject = req.getParameter("subject");
        TeamRace resultToReport = new GetEntry<TeamRace>().parseEntry(new GenericDao<>(TeamRace.class), req, logger);;

        if (req.getParameter("teamTextArea") != null && !req.getParameter("teamTextArea").isEmpty()
                && req.getParameter("subject") != null && !req.getParameter("subject").isEmpty()) {

            try {

                User user = (User) req.getSession().getAttribute("user");
                String description = req.getParameter("teamTextArea");
                subject += " regarding "
                        + resultToReport.getTeam().getName() + " in race "
                        + resultToReport.getRace().getName();

                TLSEmail mail = new TLSEmail();
                mail.simpleEmailWithTLS(subject, description, user.getEmail());

                req.setAttribute("resultReported", resultToReport.getTeam().getName());

            } catch (Exception e) {

                logger.error("There was an issue reporting this race", e);
            }

        } else {

            req.setAttribute("missingField", "Fields can't be empty");
        }

        //Forwards to the page
        new ForwardEntry<>("/reportResult.jsp", req, resp, resultToReport, null);
    }
}
