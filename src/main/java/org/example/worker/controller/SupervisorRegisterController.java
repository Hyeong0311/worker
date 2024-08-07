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

@WebServlet(value = "/page/admin/mregister")
@Log4j2
public class SupervisorRegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("supervisor register get");

        req.getRequestDispatcher("/WEB-INF/mregister.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("supervisor register post");

        String sid = req.getParameter("sid");
        String spw = req.getParameter("spw");
        String dept = req.getParameter("dept");

        try {
            if (SupervisorDAO.INSTANCE.isSupervisorIdExist(sid)) {
                req.setAttribute("error", "이미 사용중인 ID입니다. 다른 ID를 입력해주세요");
                req.getRequestDispatcher("/WEB-INF/mregister.jsp").forward(req, resp);
                return;
            }

            SupervisorVO supervisor = SupervisorVO.builder()
                    .sid(sid)
                    .spw(spw)
                    .dept(dept)
                    .sdelflag(false)
                    .build();

            SupervisorDAO.INSTANCE.sInsert(supervisor);
            resp.sendRedirect("/page/admin"); // 성공 후 admin 페이지로 리디렉션
        } catch (Exception e) {
            resp.sendRedirect("/page/admin?error=input");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
