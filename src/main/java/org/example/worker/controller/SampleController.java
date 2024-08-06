package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.dao.SupervisorDAO;
import org.example.worker.vo.SupervisorVO;

import java.io.IOException;

@Log4j2
@WebServlet(value = "/supervisor")
public class SampleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doGet");
        req.getRequestDispatcher("/WEB-INF/supervisor/supervisor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        String spw = req.getParameter("spw");
        String dept = req.getParameter("dept");

        log.info("sid = " + sid + " spw = " + spw + " dept = " + dept);

        SupervisorVO managerVO = SupervisorVO.builder()
                .sid(sid)
                .spw(spw)
                .dept(dept)
                .sdelflag(false)
                .build();

        try {
            Integer mno = SupervisorDAO.INSTANCE.mInsert(managerVO);
            resp.sendRedirect("/supervisor");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
