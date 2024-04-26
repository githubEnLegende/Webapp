package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.model.Stagiaire;
import org.oxyl.newro.Page;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.oxyl.persistence.StagiaireDAO.getAllStagiaires;
import static org.oxyl.persistence.StagiaireDAO.getPageStagiaire;
import static org.oxyl.persistence.UtilitairesDAO.getTotalPages;


@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        Page<Stagiaire> page = new Page<>();

        getPageStagiaire(1, page);

        int totalPages = getTotalPages("intern", page.getNbRow());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("stagiaires", page.getStagiaires());

        request.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(request, response);
    }
}
