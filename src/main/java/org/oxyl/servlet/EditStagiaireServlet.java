package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.context.Context;
import org.oxyl.dto.StagiaireDTO;
import org.oxyl.mapper.MapperDate;
import org.oxyl.mapper.MapperPromotion;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.persistence.DataSource;
import org.oxyl.persistence.PromotionDAO;
import org.oxyl.persistence.StagiaireDAO;
import org.oxyl.persistence.UtilitairesDAO;
import org.oxyl.validator.ValidatorStagiaire;
import org.springframework.context.ApplicationContext;
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
    private ValidatorStagiaire validatorStagiaire;
    private MapperDate mapperDate;
    ApplicationContext context = Context.getInstance().getContext();

    public void init(){
        stagiaireDAO = context.getBean(StagiaireDAO.class);
        promotionDAO = context.getBean(PromotionDAO.class);
        validatorStagiaire = context.getBean(ValidatorStagiaire.class);
        mapperDate = context.getBean(MapperDate.class);
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
        String[] promotion = request.getParameter("promotion").replace("[", "").replace("]", "").split(",");

        StagiaireDTO stagiaire = new StagiaireDTO(lastName, firstName, arrival, finFormation, promotion[0]);
        Map<Integer, String> stagiaireValidator = validatorStagiaire.stagiaireValidator(stagiaire);

        if (stagiaireValidator.isEmpty()) {

            Promotion promo = new Promotion.PromotionBuilder(Integer.parseInt(promotion[0]), promotion[1]).build();

            Stagiaire intern = new Stagiaire.StagiaireBuilder(Integer.parseInt(id),
                    firstName,
                    lastName,
                    mapperDate.stringtoLocalDate(arrival)).formationOver(mapperDate.stringtoLocalDate(finFormation)).promotion(promo).build();

            stagiaireDAO.updateIntern(intern);
            response.sendRedirect("dashboard");
        } else {
            System.out.println("Stagiaire non valide");
            request.setAttribute("stagiaireValidator", stagiaireValidator);
            doGet(request, response);
            request.getRequestDispatcher("WEB-INF/editStagiaire.jsp").forward(request, response);
        }
    }
}


