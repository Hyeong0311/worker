package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.CookieUtil;
import org.example.worker.common.StringUtil;
import org.example.worker.dao.WorkerDAO;
import org.example.worker.vo.WorkerVO;

import java.io.IOException;

@WebServlet (value="/page/normal/wregister")
@Log4j2
public class WorkerRegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("wregister get");

        req.getRequestDispatcher("/WEB-INF/wregister.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("wregister post");

        String widStr = req.getParameter("wid");
        Integer wid = StringUtil.getInt(widStr,-1);
        String wname = req.getParameter("wname");
        String sid = req.getParameter("sid");

        Cookie sidCookie = CookieUtil.getCookie(req, "supervisor");

        if(sidCookie == null){
            log.info("cookies is empty--------------");
            resp.sendRedirect("/login/supervisor");
            return;
        }

        String cookieSid = sidCookie.getValue();

        if (cookieSid == null || !cookieSid.equals(sid)) {
            log.info("not equals sid");
            resp.sendRedirect("/login/supervisor?error=invalidSid");
            return;
        }


        WorkerVO worker = WorkerVO.builder()
                .wid(wid)
                .wname(wname)
                .sid(sid)
                .build();




        try {
            WorkerDAO.INSTANCE.wInsert(worker);
            resp.sendRedirect("/page/normal?sid=" + cookieSid);
        } catch (Exception e) {
            resp.sendRedirect("/page/normal?error=input");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
