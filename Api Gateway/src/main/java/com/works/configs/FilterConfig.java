package com.works.configs;

import io.micrometer.tracing.Tracer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final Tracer tracer;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        String roles = "";
        String url = request.getRequestURI();
        if (auth != null) {
            username = auth.getName();
            roles = auth.getAuthorities().toString();
        }
        System.out.println(url + " - " + username + " - " + roles);
        String spanId = tracer.currentSpan().context().spanId();
        String traceId = tracer.currentSpan().context().traceId();
        response.setHeader("spanId", spanId);
        response.setHeader("traceId", traceId);

        filterChain.doFilter(request, response);
    }

}
