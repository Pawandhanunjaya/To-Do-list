package com.niit.taskservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpServletRequest.getHeader("authorization");
        //
        if("OPTIONS".equals(httpServletRequest.getMethod())){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        else if(authHeader==null || !authHeader.startsWith("Bearer ")){
            throw new ServletException("Missing or Invalid exception");
        }

        String token = authHeader.substring(7); // removes 'Bearer ' from token value
        Claims claims = Jwts.parser().setSigningKey("mykey").parseClaimsJws(token).getBody();

        httpServletRequest.setAttribute("claims",claims);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
