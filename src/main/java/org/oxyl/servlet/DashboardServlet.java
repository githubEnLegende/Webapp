package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.context.Context;
import org.oxyl.model.Stagiaire;
import org.oxyl.model.Page;
import org.oxyl.service.InternService;
import org.oxyl.service.UtilitairesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;


import java.io.IOException;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

    private InternService internService;
    private UtilitairesService utilitairesService;
    private final ApplicationContext context = Context.getInstance().getContext();

    public void init(){
        internService = context.getBean(InternService.class);
        utilitairesService = context.getBean(UtilitairesService.class);
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
        int countStagiaire = internService.countStagiaire();
        request.setAttribute("countStagiaire", countStagiaire);

        int totalPages = 0;
        if (search != null && !search.isEmpty()) {
            int countSize = internService.getPageStagiaire(search, page);
            request.setAttribute("countStagiaire", countSize);
            totalPages = utilitairesService.getTotalPages(countSize, page.getNbRow());

        } else {
            internService.getPageStagiaire(page);
            totalPages = utilitairesService.getTotalPages("intern", page.getNbRow());
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
                internService.deleteIntern(Integer.parseInt(id));
            }
        }
        request.setAttribute("isDeleted", isDeleted);
        response.sendRedirect("dashboard");

    }
}
