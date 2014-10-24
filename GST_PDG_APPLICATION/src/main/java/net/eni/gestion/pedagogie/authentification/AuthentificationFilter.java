package net.eni.gestion.pedagogie.authentification;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.eni.gestion.pedagogie.commun.composant.PropertyFileLoader;
import net.eni.gestion.pedagogie.commun.composant.UnauthorizedException;
import net.eni.gestion.pedagogie.configuration.ApplicationConfiguration;

public class AuthentificationFilter implements Filter {

	private PropertyFileLoader propertyFileLoader = PropertyFileLoader
			.getInstance("configuration");

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			HttpServletRequest request = ((HttpServletRequest) servletRequest);
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			HttpSession session = ((HttpServletRequest) servletRequest).getSession();
			Cookie cookie = null;
			if (request.getParameter("logout") != null) {
				session.invalidate();
				throw new UnauthorizedException();
			}
			String auth = request.getHeader("Authorization");
			if (ApplicationConfiguration.DEV_MODE.equals(propertyFileLoader.getValue("application.mode"))){
				filterChain.doFilter(request, response);
			}
			else if (auth == null) {
				Cookie[] cookies = request.getCookies();
				if (cookies == null) {
					throw new UnauthorizedException();
				}
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals(
							propertyFileLoader
									.getValue("authentication.cookie.name"))) {
						cookie = cookies[i];
					}
				}
				if (cookie == null) {
					throw new UnauthorizedException();
				}
				// TODO : Décrypter le cookie puis authentifier
				String token = cookie.getValue();
				//TODO : regénérer un cookie avec user mot de passe et date d'expiration
				response.addHeader("Authorization", token);
				filterChain.doFilter(request, response);
			}
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
}