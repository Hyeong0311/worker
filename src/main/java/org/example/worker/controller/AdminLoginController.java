package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.worker.dao.AdminDAO;
import org.example.worker.vo.AdminVO;

import java.io.IOException;

@WebServlet("/login/admin")
public class AdminLoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("pw");

        AdminDAO adminDAO = AdminDAO.INSTANCE;
        AdminVO admin = null;
        try {
            admin = adminDAO.getAdminByIdAndPassword(id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (admin != null) {
            // 로그인 성공
            req.getSession().setAttribute("admin", admin);
            resp.sendRedirect(req.getContextPath() + "/page/admin");
        } else {
            // 로그인 실패
            req.setAttribute("error", "Invalid ID or Password");
            req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
        }
    }
}
