package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.worker.dao.AdminDAO;
import org.example.worker.dao.SupervisorDAO;
import org.example.worker.vo.AdminVO;
import org.example.worker.vo.SupervisorVO;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/page/admin")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Admin 목록을 가져오는 부분
            List<AdminVO> adminList = AdminDAO.INSTANCE.getAdminList();
            req.setAttribute("adminList", adminList);

            // Supervisor 목록을 가져오는 부분
            List<SupervisorVO> supervisorList = SupervisorDAO.INSTANCE.getSupervisorList();
            req.setAttribute("supervisorList", supervisorList);

            // JSP 페이지로 전달
            req.getRequestDispatcher("/WEB-INF/admin.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}