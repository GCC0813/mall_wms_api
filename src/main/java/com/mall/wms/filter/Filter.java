package com.mall.wms.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static com.mall.wms.comm.CodeMsg.LANDING_FAILURE;
import static com.mall.wms.vo.JsonOut.ok;

/**
 * @author haonan
 * 过滤器
 */
//@Configuration
public class Filter implements javax.servlet.Filter {

	@Autowired
	HttpSession httpSession;

	@Autowired
	ObjectMapper mapper;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "0");
		response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,Access-Control-Allow-Headers");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("XDomainRequestAllowed","1");

		String apiUrl = request.getRequestURI();
		String method = request.getMethod();
		if(FilterConstant.isSpecialAPI(apiUrl) || "OPTIONS".equals(method)){
			chain.doFilter(request, response);
			return;
		}
		if(Objects.isNull(httpSession.getAttribute("user"))){
			FilterConstant.outBusinessErr(response, ok(LANDING_FAILURE), mapper);
			return;
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}
}
