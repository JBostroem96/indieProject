package edu.controller;

import edu.matc.entity.Category;
import edu.matc.entity.Race;
import edu.matc.entity.Teams;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class AddRaceResultById extends HttpServlet {

/**
     * This class' purpose is to edit a race and
     * display the result for the user
     */
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



            RequestDispatcher dispatcher = req.getRequestDispatcher("/editRaceResults.jsp");
            dispatcher.forward(req, resp);
        }
}
