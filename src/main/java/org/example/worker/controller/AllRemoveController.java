package org.example.worker.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.CookieUtil;
import org.example.worker.dao.WorkerDAO;

import java.io.IOException;

@WebServlet(value="/page/normal/allremove")
@Log4j2
public class AllRemoveController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("all remove doGet");

        Cookie sidCookie = CookieUtil.getCookie(req,"supervisor");
        if(sidCookie != null) {
            String cookieSid = sidCookie.getValue();
            req.setAttribute("cookieSid", cookieSid);
        }

        req.getRequestDispatcher("/WEB-INF/allremove.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("all remove doPost");

        Cookie sidCookie = CookieUtil.getCookie(req,"supervisor");
        String cookieSid = sidCookie.getValue();

        try {
            WorkerDAO.INSTANCE.allRemove(cookieSid);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/page/normal?sid="+cookieSid);
    }
}
