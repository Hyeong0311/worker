package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.CookieUtil;
import org.example.worker.common.PageInfo;
import org.example.worker.common.StringUtil;
import org.example.worker.dao.ScheduleDAO;
import org.example.worker.dao.WorkerDAO;
import org.example.worker.vo.ScheduleVO;
import org.example.worker.vo.SupervisorDTO;
import org.example.worker.vo.WorkerVO;

import java.io.IOException;
import java.util.List;

@WebServlet("/page/normal")
@Log4j2
public class NormalController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Handling request");

        // Extract parameters from the request
        String pageStr = req.getParameter("page");
        String sid = req.getParameter("sid");

        Cookie cks = CookieUtil.getCookie(req,"supervisor");

        String cksCookie = cks.getValue();

        req.setAttribute("cksCookie", cksCookie);

        int page = StringUtil.getInt(pageStr, 1);

        try {
            // Get total count and page info
            int total = WorkerDAO.INSTANCE.getTotal();
            PageInfo pageInfo = new PageInfo(page, 10, total);

            // Fetch worker list
            List<WorkerVO> workvoList = WorkerDAO.INSTANCE.wListSuper(sid, pageInfo.getPage());

            // Set attributes for the request
            req.setAttribute("workvoList", workvoList);
            req.setAttribute("PageInfo", pageInfo);

            // Forward request to JSP
            req.getRequestDispatcher("/WEB-INF/normal.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
