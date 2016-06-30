package com.m.weatherapp.rest.controller;


import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AllowCrossOriginRequestsFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AllowCrossOriginRequestsFilter.class);
    // To work with both normal and test domains
    private static final List<String> allowedDomains = Arrays.asList("http://localhost");

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        LOG.debug("Adding necessary headers to allow cross origin request request");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String originDomain = request.getHeader("Origin");

        if(originDomain != null && allowedDomains.contains(originDomain)){

            HttpServletResponse response = (HttpServletResponse) servletResponse;

            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", originDomain);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");

            response.setStatus(HttpServletResponse.SC_OK);

            if(request.getMethod().equals("OPTIONS")){
                LOG.debug("It is an OPTIONS request, Will not continue the filter chain");
                return;
            }
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
