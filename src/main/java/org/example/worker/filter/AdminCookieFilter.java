package org.example.worker.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.CookieUtil;

import java.io.IOException;

@WebFilter(urlPatterns = {"/page/admin","/page/admin/mregister"})
@Log4j2
public class AdminCookieFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie cks = CookieUtil.getCookie(request,"admin");

        if(cks == null || cks.getValue() == null){
            response.sendRedirect("/main");
            log.info("no cookie admin");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}