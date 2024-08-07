package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.dao.ScheduleDAO;
import org.example.worker.vo.HRListDTO;
import org.example.worker.vo.ScheduleVO;

import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(value = "/page/hrlist")
public class HRController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("HRController");

        try {
            List<HRListDTO> scheduleList = ScheduleDAO.INSTANCE.HRList();
            req.setAttribute("list", scheduleList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/WEB-INF/hr.jsp").forward(req, resp);
    }
}
