package edu.controller;

import edu.matc.entity.Race;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/searchRace"}
)

public class SearchRace extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Race.class);

        if (req.getParameter("submit").equals("search")) {

            if (req.getParameter("searchType").equals("length")) {

                req.setAttribute("races", dao.findByPropertyEqual("length", req.getParameter("searchTerm")));

            } else if (req.getParameter("searchType").equals("name"))  {

                req.setAttribute("races", dao.findByPropertyEqual("name", req.getParameter("searchTerm")));

            } else {

                req.setAttribute("races", dao.findByPropertyEqual("date", req.getParameter("searchTerm")));
            }

        }
        else  {

            req.setAttribute("races", dao.getAll());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchResults.jsp");
        dispatcher.forward(req, resp);
    }
}
