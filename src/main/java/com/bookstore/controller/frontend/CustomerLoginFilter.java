package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/*")
public class CustomerLoginFilter extends HttpFilter implements Filter {
       
	private static final String[] loginRequiredURLs = {
			"/view_profile", "/edit_profile", "/update_profile", "/write_review"
	};
	private static final long serialVersionUID = 1L;

	public CustomerLoginFilter() {
        super();
    }

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		

		if(!path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		
		boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;
		
		String requestURL = httpRequest.getRequestURL().toString();
		
		System.out.println("The path is "+path);
		System.out.println("The logged in " +loggedIn);
		
		if(!loggedIn && isLoginRequired(requestURL)) {
			
			session.setAttribute("redirectURL", requestURL);
			String loginPage = "frontend/login.jsp";
			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
			
			
		} else {
			chain.doFilter(request, response);
		}
		
	}

	private boolean isLoginRequired(String requestURL) {
		for(String loginRequiredURL : loginRequiredURLs) {
				if(requestURL.contains(loginRequiredURL)) {
					return true;
				}
		}
		return false;
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
