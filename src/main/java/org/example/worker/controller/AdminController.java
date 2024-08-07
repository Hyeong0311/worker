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
import org.example.worker.vo.SupervisorVO;

import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(value = "/page/admin")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            List<AdminVO> adminList = AdminDAO.INSTANCE.getAdminList();
            List<SupervisorVO> supervisorList = SupervisorDAO.INSTANCE.getSupervisorList();

            req.setAttribute("adminList", adminList);
            req.setAttribute("supervisorList", supervisorList);

            req.getRequestDispatcher("/WEB-INF/admin.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
