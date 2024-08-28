package edu.controller;

import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.PropertiesLoader;
import edu.matc.util.UseLogger;
import edu.restService.TLSEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class ReportResultById extends HttpServlet implements UseLogger {

    /**
     * This method's purpose is to submit the report and send the email
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = log();
        GenericDao<TeamRace> dao = new GenericDao<>(TeamRace.class);

        String description = null;
        String subject = req.getParameter("subject");
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

                TeamRace resultToReport = dao.getById(Integer.parseInt(id));

                if (req.getParameter("teamTextArea") != null && !req.getParameter("teamTextArea").isEmpty()
                        && req.getParameter("subject") != null & !req.getParameter("subject").isEmpty()) {

                    description = req.getParameter("teamTextArea");
                    subject += " regarding "
                            + resultToReport.getTeam().getName() + " in race "
                            + resultToReport.getRace().getName();
                }

                req.setAttribute("resultReported", resultToReport.getTeam().getName());
                req.setAttribute("result", resultToReport);

                TLSEmail mail = new TLSEmail();

                mail.simpleEmailWithTLS(subject, description);

            } catch (Exception e) {

                logger.error("Something went wrong!", e);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/reportResult.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
