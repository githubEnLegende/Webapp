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
import org.oxyl.service.PromotionService;
import org.oxyl.service.InternService;
import org.oxyl.validator.ValidatorStagiaire;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@WebServlet("/editStagiaire")
public class EditStagiaireServlet extends HttpServlet {

    private PromotionService promotionService;
    private InternService internService;
    private ValidatorStagiaire validatorStagiaire;
    private MapperDate mapperDate;
    ApplicationContext context = Context.getInstance().getContext();

    public void init(){
        internService = context.getBean(InternService.class);
        promotionService = context.getBean(PromotionService.class);
        validatorStagiaire = context.getBean(ValidatorStagiaire.class);
        mapperDate = context.getBean(MapperDate.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Optional<Stagiaire> optStagiaire = internService.detailStagiaire(id);
        if (optStagiaire.isPresent()) {
            Stagiaire stagiaire = optStagiaire.get();
            request.setAttribute("stagiaire", stagiaire);
        }


        List<Promotion> listPromo = promotionService.getAllPromotion();
        request.setAttribute("listPromo", listPromo);

        request.getRequestDispatcher("WEB-INF/editStagiaire.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String arrival = request.getParameter("arrival");
        String finFormation = request.getParameter("finFormation");
        String[] promotion = request.getParameter("promotion").replace("[", "").replace("]", "").split(",");

        StagiaireDTO stagiaire = new StagiaireDTO(lastName, firstName, arrival, finFormation, promotion[0]);
        Map<Integer, String> stagiaireValidator = validatorStagiaire.stagiaireValidator(stagiaire);

        if (stagiaireValidator.isEmpty()) {

            Promotion promo = new Promotion.PromotionBuilder(Integer.parseInt(promotion[0]), promotion[1]).build();

            Stagiaire intern = new Stagiaire.StagiaireBuilder(Integer.parseInt(id),
                    firstName,
                    lastName,
                    mapperDate.stringtoLocalDate(arrival)).formationOver(mapperDate.stringtoLocalDate(finFormation)).promotion(promo).build();

            internService.updateIntern(intern);
            response.sendRedirect("dashboard");
        } else {
            System.out.println("Stagiaire non valide");
            request.setAttribute("stagiaireValidator", stagiaireValidator);
            doGet(request, response);
            request.getRequestDispatcher("WEB-INF/editStagiaire.jsp").forward(request, response);
        }
    }
}


