package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.oxyl.persistence.StagiaireDAO.updateIntern;

@WebServlet("/editStagiaire")
public class EditStagiaireServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


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


        int stagiaireId = 0;
        if(id != null && !id.isEmpty()){
            stagiaireId = Integer.parseInt(request.getParameter("id"));
        }

        int promoId = 0;
        if(promotionId != null && !promotionId.isEmpty()) {
            promoId = Integer.parseInt(promotionId);
        }

        updateIntern(firstName, lastName, arrival, finFormation, promoId, stagiaireId);
        response.sendRedirect("dashboard");
    }

}
