package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.dao.SupervisorDAO;
import org.example.worker.vo.SupervisorVO;

import java.io.IOException;
import java.util.Optional;

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
        log.info("supervisor doPost");

        String sid = req.getParameter("sid");
        String spw = req.getParameter("spw");
        String dept = req.getParameter("dept");

        try {
            Optional<SupervisorVO> result = SupervisorDAO.INSTANCE.get(sid,spw,dept);

            result.ifPresentOrElse( supervisorVO -> {
                Cookie superCookie = new Cookie("supervisor", sid);
                superCookie.setPath("/");
                superCookie.setMaxAge(3600);

                resp.addCookie(superCookie);

                try {
                    resp.sendRedirect("/page/normal?sid="+sid); //admin,hr,normal세가지로 변경해줘야함
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                try {
                    resp.sendRedirect("/main");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
