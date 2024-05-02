package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.model.Stagiaire;
import org.oxyl.newro.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.oxyl.persistence.StagiaireDAO.deleteIntern;
import static org.oxyl.persistence.StagiaireDAO.getPageStagiaire;
import static org.oxyl.persistence.UtilitairesDAO.getTotalPages;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Page<Stagiaire> page = new Page<>();
        String pageParam = request.getParameter("page");
        String pageTaille = request.getParameter("size");

        int pageNum = 1;
        if (pageParam != null) {
            try{
                pageNum = Integer.parseInt(pageParam);
            }catch (NumberFormatException e){
                logger.error("Mauvais format de nombre pour la page", e);
            }
        }

        if(pageTaille != null && !pageTaille.isEmpty()){
            page.setNbRow(Integer.parseInt(pageTaille));
        }
        getPageStagiaire(pageNum, page);

        int totalPages = getTotalPages("intern", page.getNbRow());

        request.setAttribute("size", page.getNbRow());
        request.setAttribute("page", pageNum);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("stagiaires", page.getStagiaires());

        request.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(request, response);

        page.emptyContent();


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isDeleted = false;
        if(!request.getParameter("selection").isEmpty()) {
            String[] idsToDelete = request.getParameter("selection").split(",");
            for (String id : idsToDelete) {
                deleteIntern(Integer.parseInt(id));
            }
        }
        request.setAttribute("isDeleted", isDeleted);
        response.sendRedirect("dashboard");

    }
}
