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

        try {
            SupervisorVO supervisorVO = SupervisorDAO.INSTANCE.loginSupervisor(sid, dept);
            if (supervisorVO != null && supervisorVO.getSpw().equals(spw)) {
                log.info("로그인 성공 - 부서명: {}, 아이디: {}", dept, sid);
                resp.sendRedirect("/nextPage"); // 로그인 성공 후 이동할 페이지
            } else {
                log.info("로그인 실패 - 잘못된 아이디 또는 비밀번호");
                req.setAttribute("errorMessage", "잘못된 아이디 또는 비밀번호입니다.");
                req.getRequestDispatcher("/WEB-INF/slogin.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error("로그인 중 오류 발생", e);
            throw new RuntimeException(e);
        }
    }
}
