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
@WebServlet(value = "/login/supervisor")
public class SupervisorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        req.getRequestDispatcher("/WEB-INF/slogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dept = req.getParameter("dept");
        String sid = req.getParameter("sid");
        String spw = req.getParameter("spw");

        log.info("받은 파라미터 - 부서명: {}, 아이디: {}, 비밀번호: {}", dept, sid, spw);

        SupervisorVO managerVO = SupervisorVO.builder()
                .sid(sid)
                .spw(spw)
                .dept(dept)
                .build();

        try {
            SupervisorDAO.INSTANCE.mInsert(managerVO);
            resp.sendRedirect("/slogin");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
