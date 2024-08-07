package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.StringUtil;
import org.example.worker.dao.ScheduleDAO;
import org.example.worker.vo.HRListDTO;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

@Log4j2
@WebServlet(value = "/page/hr/list")
public class HRListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String widStr = req.getParameter("wid");
        Integer wid = StringUtil.getInt(widStr, -1);

        try{
            Optional<HRListDTO> inTime = ScheduleDAO.INSTANCE.getIntime(wid);
            HRListDTO inDTO = inTime.orElseThrow();

            Optional<HRListDTO> outTime = ScheduleDAO.INSTANCE.getOuttime(wid);
            HRListDTO outDTO = outTime.orElseThrow();

            log.info(inDTO.toString());
            log.info(outDTO.toString());




            req.getRequestDispatcher("/WEB-INF/hrlist.jsp").forward(req, resp);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
