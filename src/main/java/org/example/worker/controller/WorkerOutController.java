package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.dao.ScheduleDAO;
import org.example.worker.vo.ScheduleVO;

import java.io.IOException;

@Log4j2
@WebServlet(value = "/login/worker/out")
public class WorkerOutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer wid = Integer.parseInt(req.getParameter("wid"));

        log.info(wid);

        try {
            ScheduleDAO.INSTANCE.stime(wid);
            resp.sendRedirect("/main");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
