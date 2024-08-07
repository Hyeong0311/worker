package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.PageInfo;
import org.example.worker.common.StringUtil;
import org.example.worker.dao.ScheduleDAO;
import org.example.worker.vo.HRListDTO;
import org.example.worker.vo.ScheduleVO;

import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(value = "/page/hr")
public class HRController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageStr = req.getParameter("page");

        int page = StringUtil.getInt(pageStr, 1);

        try {
            List<HRListDTO> scheduleList = ScheduleDAO.INSTANCE.HRList(page);

            int total = ScheduleDAO.INSTANCE.getTotal();

            PageInfo pageInfo = new PageInfo(page, 10, total);

            req.setAttribute("list", ScheduleDAO.INSTANCE.HRList(pageInfo.getPage()));
            req.setAttribute("pageInfo", pageInfo);
            req.getRequestDispatcher("/WEB-INF/hr.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
