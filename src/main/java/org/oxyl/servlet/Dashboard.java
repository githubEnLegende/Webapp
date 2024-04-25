package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.model.Stagiaire;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.oxyl.persistence.StagiaireDAO.getAllStagiaires;


@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Optional<List<Stagiaire>> optStagiaires = getAllStagiaires();
        List<Stagiaire> stagiaires = null;
        if (optStagiaires.isPresent()) {
            stagiaires = optStagiaires.get();
        }

        request.setAttribute("stagiaires", stagiaires);

        request.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(request, response);
    }
}
