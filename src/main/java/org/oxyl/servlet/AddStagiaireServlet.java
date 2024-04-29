package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.model.Stagiaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;

import static org.oxyl.persistence.StagiaireDAO.insertIntern;
import static org.oxyl.persistence.UtilitairesDAO.getMaxID;


@WebServlet("/addStagiaire")
public class AddStagiaireServlet extends HttpServlet {

    private final static Logger logger = LoggerFactory.getLogger(AddStagiaireServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/addStagiaire.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String arrival = request.getParameter("arrival");
        String finFormation = request.getParameter("finFormation");
        String promotionId = request.getParameter("promotionId");

        Stagiaire stagiaire = new Stagiaire.StagiaireBuilder(getMaxID()+1, firstName, lastName, LocalDate.parse(arrival))
                .formationOver(LocalDate.parse(finFormation))
                .promotion(Integer.parseInt(promotionId)).build();

        insertIntern(stagiaire);
        response.sendRedirect("dashboard");
    }

}
