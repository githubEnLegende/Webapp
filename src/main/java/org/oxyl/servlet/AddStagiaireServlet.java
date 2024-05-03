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
import org.oxyl.persistence.PromotionDAO;
import org.oxyl.persistence.StagiaireDAO;
import org.oxyl.persistence.UtilitairesDAO;
import org.oxyl.validator.ValidatorStagiaire;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;



@WebServlet("/addStagiaire")
public class AddStagiaireServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Promotion> listPromo = PromotionDAO.getInstance().getAllPromotion();
        request.setAttribute("listPromo", listPromo);

        request.getRequestDispatcher("WEB-INF/addStagiaire.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String arrival = request.getParameter("arrival");
        String finFormation = request.getParameter("finFormation");
        String promotionId = request.getParameter("promotionId");


        StagiaireDTO stagiaire = new StagiaireDTO(lastName, firstName, arrival, finFormation, promotionId);

        Map<Integer, String> stagiaireValidator = ValidatorStagiaire.getInstance().stagiaireValidator(stagiaire);

        if (stagiaireValidator.isEmpty()){

            LocalDate finFormationDate =  MapperDate.getInstance().stringtoLocalDate(finFormation);

            Stagiaire intern = new Stagiaire.StagiaireBuilder(UtilitairesDAO.getInstance().getMaxID()+1,
                    firstName, lastName, LocalDate.parse(arrival))
                    .formationOver(finFormationDate)
                    .promotion(Integer.parseInt(promotionId)).build();

            StagiaireDAO.getInstance().insertIntern(intern);
            response.sendRedirect("dashboard");
        }else{
            System.out.println("Stagiaire non valide");
            request.setAttribute("stagiaireValidator", stagiaireValidator);
            doGet(request, response);
            request.getRequestDispatcher("WEB-INF/addStagiaire.jsp").forward(request, response);
        }


    }

}
