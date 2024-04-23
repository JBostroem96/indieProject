package edu.controller;

import edu.matc.entity.Category;
import edu.matc.persistence.GenericDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class' purpose is to forward to the JSP page
 * @author Jimmy Bostroem
 */
@WebServlet(
        name = "/addCategory",
        urlPatterns = { "/addCategory" }
)
public class AddCategory extends HttpServlet {

    /**
     * This method's purpose is to grab the category from the field and add it to the table
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Category.class);
        Category category = new Category(req.getParameter("category"));

        dao.insert(category);

        req.setAttribute("category", category);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addCategoryResult.jsp");
        dispatcher.forward(req, resp);
    }
}
