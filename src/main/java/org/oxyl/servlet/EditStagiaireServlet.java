package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.persistence.StagiaireDAO;
import org.oxyl.validator.ValidatorStagiaire;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.oxyl.persistence.PromotionDAO.getAllPromotion;
import static org.oxyl.persistence.StagiaireDAO.*;
import static org.oxyl.persistence.UtilitairesDAO.getMaxID;

@WebServlet("/editStagiaire")
public class EditStagiaireServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Optional<Stagiaire> optStagiaire = StagiaireDAO.detailStagiaire(id);
        if (optStagiaire.isPresent()) {
            Stagiaire stagiaire = optStagiaire.get();
            request.setAttribute("stagiaire", stagiaire);
        }


        List<Promotion> listPromo = getAllPromotion();
        request.setAttribute("listPromo", listPromo);

        request.getRequestDispatcher("WEB-INF/editStagiaire.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String arrival = request.getParameter("arrival");
        String finFormation = request.getParameter("finFormation");
        String promotionId = request.getParameter("promotionId");


        LocalDate finFormationDate = null;

        if (finFormation != null && !finFormation.isEmpty()) {
            finFormationDate = LocalDate.parse(finFormation);
        }

        Stagiaire stagiaire = new Stagiaire.StagiaireBuilder(getMaxID()+1, firstName, lastName, LocalDate.parse(arrival))
                .formationOver(finFormationDate)
                .promotion(Integer.parseInt(promotionId)).build();

        ValidatorStagiaire stagaireValidator = new ValidatorStagiaire(stagiaire);

        if (stagaireValidator.isValide()){
            insertIntern(stagiaire);
            response.sendRedirect("dashboard");
        }else{
            System.out.println("Stagiaire non valide");
            request.setAttribute("stagiaireValidator", stagaireValidator);
            request.getRequestDispatcher("WEB-INF/editStagiaire.jsp").forward(request, response);
        }
    }

}
