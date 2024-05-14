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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@WebServlet("/addStagiaire")
public class AddStagiaireServlet extends HttpServlet {

    private MapperDate mapperDate;
    private PromotionDAO promotionDAO;
    private StagiaireDAO stagiaireDAO;
    private UtilitairesDAO utilitairesDAO;

    public void init(){
        var context = new AnnotationConfigApplicationContext(DataSource.class);
        mapperDate = context.getBean(MapperDate.class);
        stagiaireDAO = context.getBean(StagiaireDAO.class);
        promotionDAO = context.getBean(PromotionDAO.class);
        utilitairesDAO = context.getBean(UtilitairesDAO.class);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Promotion> listPromo = promotionDAO.getAllPromotion();
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

        if (stagiaireValidator.isEmpty()) {

            LocalDate finFormationDate = mapperDate.stringtoLocalDate(finFormation);

            Stagiaire intern = new Stagiaire.StagiaireBuilder(utilitairesDAO.getMaxID() + 1,
                    firstName, lastName, LocalDate.parse(arrival))
                    .formationOver(finFormationDate)
                    .promotion(Integer.parseInt(promotionId)).build();

            stagiaireDAO.insertIntern(intern);
            response.sendRedirect("dashboard");
        } else {
            System.out.println("Stagiaire non valide");
            request.setAttribute("stagiaireValidator", stagiaireValidator);
            doGet(request, response);
            request.getRequestDispatcher("WEB-INF/addStagiaire.jsp").forward(request, response);
        }


    }

}
