package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.LoginUtil;
import org.example.worker.vo.SupervisorVO;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value="/login/hr")
@Log4j2
public class HRLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info(req.getParameter("id"));
        log.info(req.getParameter("pw"));

        String sid = req.getParameter("id");
        String spw = req.getParameter("pw");

        try {
            Optional<SupervisorVO> result = LoginUtil.INSTANCE.getHR(sid,spw);
            result.ifPresentOrElse(SupervisorVO -> {
                Cookie superCookie = new Cookie("hr", sid);
                superCookie.setPath("/");
                superCookie.setMaxAge(3600);

                resp.addCookie(superCookie);
                try {
                    resp.sendRedirect("/page/hr");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, ()->{
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
