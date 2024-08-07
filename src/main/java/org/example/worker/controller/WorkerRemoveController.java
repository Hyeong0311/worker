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
import java.util.Optional;

@Log4j2
@WebServlet(value="/page/normal/wremove")
public class WorkerRemoveController  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("remove get");

        String wnoStr = req.getParameter("wno");
        Integer wno = StringUtil.getInt(wnoStr,-1);

        Cookie sidCookie = CookieUtil.getCookie(req,"supervisor");
        String cookieSid = sidCookie.getValue();
        req.setAttribute("cookieSid",cookieSid);

        try {
            Optional<WorkerVO> result = WorkerDAO.INSTANCE.getworker(wno);

            WorkerVO vo = result.orElseThrow();

            req.setAttribute("worker", vo);

            req.getRequestDispatcher("/WEB-INF/wremove.jsp").forward(req, resp);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("remove post");

        String wnoStr = req.getParameter("wno");
        Integer wno = StringUtil.getInt(wnoStr,-1);

        Cookie sidCookie = CookieUtil.getCookie(req,"supervisor");
        String cookieSid = sidCookie.getValue();

        try {
            WorkerDAO.INSTANCE.wRemove(wno);
            log.info("remove success");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/page/normal?sid="+cookieSid);

    }
}
