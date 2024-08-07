package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.dao.SupervisorDAO;

import java.io.IOException;

@WebServlet("/page/admin/deleteSupervisor")
@Log4j2
public class SupervisorDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");

        try {
            SupervisorDAO.INSTANCE.deleteSupervisor(sid);
            log.info("Supervisor with ID {} deleted", sid);
            resp.sendRedirect("/page/admin");
        } catch (Exception e) {
            log.error("Error deleting supervisor", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting supervisor");
        }
    }
}
