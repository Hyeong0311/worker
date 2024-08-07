package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.StringUtil;
import org.example.worker.dao.ScheduleDAO;
import org.example.worker.dao.WorkerDAO;
import org.example.worker.vo.WorkerVO;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@WebServlet(value = "/login/worker/in")
public class WorkerInController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer wid = Integer.parseInt(req.getParameter("wid"));

        try {
            Optional<WorkerVO> vo = WorkerDAO.INSTANCE.getworker(wid);

            if (vo.isPresent()) {
                log.info("in - " + wid);
                ScheduleDAO.INSTANCE.stime(wid);
                resp.sendRedirect("/main");
            } else {
                log.info("No worker found with ID: " + wid);
                resp.sendRedirect("/main");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
