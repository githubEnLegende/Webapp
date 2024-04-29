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
import java.util.List;
import java.util.Optional;

import static org.oxyl.persistence.StagiaireDAO.getAllStagiaires;
import static org.oxyl.persistence.StagiaireDAO.getPageStagiaire;
import static org.oxyl.persistence.UtilitairesDAO.getTotalPages;


@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(Dashboard.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String pageParam = request.getParameter("page");
        int pageNum = 1;
        if (pageParam != null) {
            try{
                pageNum = Integer.parseInt(pageParam);
            }catch (NumberFormatException e){
                logger.error("Mauvais format de nombre pour la page", e);
            }
        }


        Page<Stagiaire> page = new Page<>();

        getPageStagiaire(pageNum, page);

        int totalPages = getTotalPages("intern", page.getNbRow());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("stagiaires", page.getStagiaires());

        request.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(request, response);

        page.emptyContent();
    }
}
