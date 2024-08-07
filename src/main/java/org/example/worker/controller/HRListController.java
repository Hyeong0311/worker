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
import org.example.worker.vo.SalaryCheckDTO;

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
            HRListDTO inTime = ScheduleDAO.INSTANCE.getIntime(wid);
            HRListDTO outTime = ScheduleDAO.INSTANCE.getOuttime(wid);
            SalaryCheckDTO result;
            String message = "";

            if(inTime == null) {
                message = "InTime Null";
                result = null;
            } else if(outTime == null) {
                message = "OutTime Null";
                result = null;
            }else {
                result = SalaryCheckDTO.builder()
                        .wname(inTime.getWname())
                        .in(inTime.getTime())
                        .out(outTime.getTime())
                        .dept(inTime.getDept())
                        .wid(inTime.getWid())
                        .build();

                int time = ScheduleDAO.INSTANCE.getTime(result.getIn(), result.getOut());
                int salary = ScheduleDAO.INSTANCE.getSalary(time);

                req.setAttribute("time", time);
                req.setAttribute("salary", salary);
            }

            req.setAttribute("message", message);
            req.setAttribute("result", result);
            req.getRequestDispatcher("/WEB-INF/hrlist.jsp").forward(req, resp);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
