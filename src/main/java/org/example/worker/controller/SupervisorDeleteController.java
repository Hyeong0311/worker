package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.dao.AdminDAO;
import org.example.worker.dao.SupervisorDAO;
import org.example.worker.vo.AdminVO;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/page/admin/deleteSupervisor")
@Log4j2
public class SupervisorDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        String password = req.getParameter("password");

        try {
            Optional<AdminVO> adminOpt = AdminDAO.INSTANCE.getAdmin("superuser", password);

            if (adminOpt.isPresent()) {
                SupervisorDAO.INSTANCE.deleteSupervisor(sid);
                log.info("Supervisor with ID {} deleted", sid);
                resp.sendRedirect("/page/admin");
            } else {
                log.warn("Invalid admin password");
                resp.sendRedirect("/page/admin?error=invalidPassword");
            }
        } catch (Exception e) {
            log.error("Error deleting supervisor", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting supervisor");
        }
    }
}
