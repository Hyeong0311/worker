package org.example.worker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.worker.common.LoginUtil;
import org.example.worker.dao.AdminDAO;
import org.example.worker.vo.AdminVO;
import org.example.worker.vo.SupervisorVO;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login/admin")
public class AdminLoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        try {
            Optional<AdminVO> result = LoginUtil.INSTANCE.getAdmin(id,pw);
            result.ifPresentOrElse(SupervisorVO -> {
                Cookie superCookie = new Cookie("admin", id);
                superCookie.setPath("/");
                superCookie.setMaxAge(3600);

                resp.addCookie(superCookie);
                try {
                    resp.sendRedirect("/page/admin");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, ()->{
                try {
                    resp.sendRedirect("/main");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
