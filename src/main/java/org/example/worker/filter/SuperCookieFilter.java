package org.example.worker.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.CookieUtil;

import java.io.IOException;

@WebFilter (urlPatterns = {"/page/normal","/page/normal/wrgister","/page/normal/wremove"})
@Log4j2
public class SuperCookieFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie cks = CookieUtil.getCookie(request,"supervisor");

        if(cks == null || cks.getValue() == null){
            response.sendRedirect("/main");
            log.info("no cookie supervisor");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
