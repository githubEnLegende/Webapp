package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.context.Context;
import org.oxyl.dto.StagiaireDTO;
import org.oxyl.mapper.MapperDate;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.service.InternService;
import org.oxyl.service.PromotionService;
import org.oxyl.service.UtilitairesService;
import org.oxyl.validator.ValidatorStagiaire;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;


@WebServlet("/addStagiaire")
public class AddStagiaireServlet extends HttpServlet {

    private MapperDate mapperDate;
    private PromotionService promotionService;
    private InternService internService;
    private UtilitairesService utilitairesService;
    private ValidatorStagiaire validatorStagiaire;
    ApplicationContext context = Context.getInstance().getContext();


    public void init(){
        mapperDate = context.getBean(MapperDate.class);
        internService = context.getBean(InternService.class);
        promotionService = context.getBean(PromotionService.class);
        utilitairesService = context.getBean(UtilitairesService.class);
        validatorStagiaire = context.getBean(ValidatorStagiaire.class);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Promotion> listPromo = promotionService.getAllPromotion();
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
        String promotionName = request.getParameter("promotionName");


        StagiaireDTO stagiaire = new StagiaireDTO(lastName, firstName, arrival, finFormation, promotionId);

        Map<Integer, String> stagiaireValidator = validatorStagiaire.stagiaireValidator(stagiaire);

        if (stagiaireValidator.isEmpty()) {

            LocalDate finFormationDate = mapperDate.stringtoLocalDate(finFormation);

            Promotion promotion = new Promotion.PromotionBuilder(Integer.parseInt(promotionId), promotionId).build();

            Stagiaire intern = new Stagiaire.StagiaireBuilder(utilitairesService.getMaxID() + 1,
                    firstName, lastName, LocalDate.parse(arrival))
                    .formationOver(finFormationDate)
                    .promotion(promotion).build();

            internService.insertIntern(intern);
            response.sendRedirect("dashboard");
        } else {
            System.out.println("Stagiaire non valide");
            request.setAttribute("stagiaireValidator", stagiaireValidator);
            doGet(request, response);
            request.getRequestDispatcher("WEB-INF/addStagiaire.jsp").forward(request, response);
        }


    }

}
