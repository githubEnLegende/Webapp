package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.model.Stagiaire;
import org.oxyl.newro.Page;
import org.oxyl.persistence.StagiaireDAO;
import org.oxyl.persistence.UtilitairesDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Entrée dans le feur");

        Page<Stagiaire> page = new Page<>();
        String pageParam = request.getParameter("page");
        String pageTaille = request.getParameter("size");
        String search = request.getParameter("search");

        if (pageParam != null) {
            try {
                page.setPageNumber(Integer.parseInt(pageParam));
            } catch (NumberFormatException e) {
                logger.error("Mauvais format de nombre pour la page", e);
            }
        }
        if (pageTaille != null && !pageTaille.isEmpty()) {
            page.setNbRow(Integer.parseInt(pageTaille));
        }

        if (search != null && !search.isEmpty()) {
            StagiaireDAO.getInstance().getPageStagiaire(search, page);
        } else {
            StagiaireDAO.getInstance().getPageStagiaire(page);
        }

        int totalPages = UtilitairesDAO.getInstance().getTotalPages("intern", page.getNbRow());

        request.setAttribute("size", page.getNbRow());
        request.setAttribute("page", page.getPageNumber());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("stagiaires", page.getStagiaires());

        request.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(request, response);

        page.emptyContent();


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isDeleted = false;
        if (!request.getParameter("selection").isEmpty()) {
            String[] idsToDelete = request.getParameter("selection").split(",");
            for (String id : idsToDelete) {
                StagiaireDAO.getInstance().deleteIntern(Integer.parseInt(id));
            }
        }
        request.setAttribute("isDeleted", isDeleted);
        response.sendRedirect("dashboard");

    }
}
