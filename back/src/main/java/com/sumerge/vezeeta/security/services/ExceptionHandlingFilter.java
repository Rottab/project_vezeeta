package com.sumerge.vezeeta.security.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExceptionHandlingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            Map<String, Object> body = new LinkedHashMap<>();
//            body.put("timestamp", LocalDateTime.now());
            body.put("message", "Invalid token");
            body.put("error", e.getMessage());
            response.getWriter().write(objectToJson(body));
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            Map<String, Object> body = new LinkedHashMap<>();
//            body.put("timestamp", LocalDateTime.now());
            body.put("error", e.getMessage());
            response.getWriter().write(objectToJson(body));
        }
    }

    public String objectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
