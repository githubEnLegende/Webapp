package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.dto.StagiaireDTO;
import org.oxyl.mapper.MapperDate;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.persistence.DataSource;
import org.oxyl.persistence.PromotionDAO;
import org.oxyl.persistence.StagiaireDAO;
import org.oxyl.persistence.UtilitairesDAO;
import org.oxyl.validator.ValidatorStagiaire;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.oxyl.persistence.StagiaireDAO.*;

@WebServlet("/editStagiaire")
public class EditStagiaireServlet extends HttpServlet {

    private PromotionDAO promotionDAO;
    private StagiaireDAO stagiaireDAO;

    public void init(){
        var context = new AnnotationConfigApplicationContext(DataSource.class);
        stagiaireDAO = context.getBean(StagiaireDAO.class);
        promotionDAO = context.getBean(PromotionDAO.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Optional<Stagiaire> optStagiaire = stagiaireDAO.detailStagiaire(id);
        if (optStagiaire.isPresent()) {
            Stagiaire stagiaire = optStagiaire.get();
            request.setAttribute("stagiaire", stagiaire);
        }


        List<Promotion> listPromo = promotionDAO.getAllPromotion();
        request.setAttribute("listPromo", listPromo);

        request.getRequestDispatcher("WEB-INF/editStagiaire.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String arrival = request.getParameter("arrival");
        String finFormation = request.getParameter("finFormation");
        String promotionId = request.getParameter("promotionId");

        StagiaireDTO stagiaire = new StagiaireDTO(lastName, firstName, arrival, finFormation, promotionId);
        Map<Integer, String> stagiaireValidator = ValidatorStagiaire.getInstance().stagiaireValidator(stagiaire);

        if (stagiaireValidator.isEmpty()) {

            int stagId = Integer.parseInt(id);
            int promoId = Integer.parseInt(promotionId);

            stagiaireDAO.updateIntern(firstName, lastName, arrival, finFormation, promoId, stagId);
            response.sendRedirect("dashboard");
        } else {
            System.out.println("Stagiaire non valide");
            request.setAttribute("stagiaireValidator", stagiaireValidator);
            doGet(request, response);
            request.getRequestDispatcher("WEB-INF/editStagiaire.jsp").forward(request, response);
        }


//        ValidatorStagiaire stagaireValidator = new ValidatorStagiaire(stagiaire);
//
//        if (stagaireValidator.isValide()){
//            insertIntern(stagiaire);
//            response.sendRedirect("dashboard");
//        }else{
//            System.out.println("Stagiaire non valide");
//            request.setAttribute("stagiaireValidator", stagaireValidator);
    }
}


