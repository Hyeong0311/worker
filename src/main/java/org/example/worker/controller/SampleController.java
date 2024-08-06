package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.dao.ManagerDAO;
import org.example.worker.vo.ManagerVO;

import java.io.IOException;

@Log4j2
@WebServlet(value = "/manager")
public class SampleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("/WEB-INF/manager/manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        String dept = req.getParameter("dept");
        ManagerVO managerVO = ManagerVO.builder()
                .mid(mid)
                .mpw(mpw)
                .dept(dept)
                .build();

        try {
            Integer mno = ManagerDAO.INSTANCE.mInsert(managerVO);
            resp.sendRedirect("/manager/manager");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
