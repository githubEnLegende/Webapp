package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.context.Context;
import org.oxyl.model.Stagiaire;
import org.oxyl.model.Page;
import org.oxyl.persistence.StagiaireDAO;
import org.oxyl.persistence.UtilitairesDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;


import java.io.IOException;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

    private StagiaireDAO stagiaireDAO;
    private UtilitairesDAO utilitairesDAO;
    ApplicationContext context = Context.getInstance().getContext();

    public void init(){
        stagiaireDAO = context.getBean(StagiaireDAO.class);
        utilitairesDAO = context.getBean(UtilitairesDAO.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Entrée dans le feur");

        Page<Stagiaire> page = new Page<>();
        String pageParam = request.getParameter("page");
        String pageTaille = request.getParameter("size");
        String search = request.getParameter("search");
        String order = request.getParameter("order");

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

        page.setOrder(SecureOrder.inEnum(order));
        int countStagiaire = stagiaireDAO.countStagiaire();
        request.setAttribute("countStagiaire", countStagiaire);

        int totalPages = 0;
        if (search != null && !search.isEmpty()) {
            int countSize = stagiaireDAO.getPageStagiaire(search, page);
            request.setAttribute("countStagiaire", countSize);
            totalPages = utilitairesDAO.getTotalPages(countSize, page.getNbRow());

        } else {
            stagiaireDAO.getPageStagiaire(page);
            totalPages = utilitairesDAO.getTotalPages("intern", page.getNbRow());
        }


        request.setAttribute("size", page.getNbRow());
        request.setAttribute("page", page.getPageNumber());
        request.setAttribute("order", page.getOrder());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("stagiaires", page.getStagiaires());
        request.setAttribute("search", search);

        request.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(request, response);

        page.emptyContent();


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isDeleted = false;
        if (!request.getParameter("selection").isEmpty()) {
            String[] idsToDelete = request.getParameter("selection").split(",");
            for (String id : idsToDelete) {
                stagiaireDAO.deleteIntern(Integer.parseInt(id));
            }
        }
        request.setAttribute("isDeleted", isDeleted);
        response.sendRedirect("dashboard");

    }
}
