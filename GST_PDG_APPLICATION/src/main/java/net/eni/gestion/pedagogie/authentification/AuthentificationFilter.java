package net.eni.gestion.pedagogie.authentification;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.eni.gestion.pedagogie.DAO.implementation.DroitProfilDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ProfilDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.UtilisateurDaoImpl;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.UnauthorizedException;
import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.service.implementation.UtilisateurServiceImpl;

import com.sun.jersey.core.util.Base64;

public class AuthentificationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			HttpServletRequest request = ((HttpServletRequest) servletRequest);
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			String auth = request.getHeader("Authorization");
			Utilisateur lUtilisateurAuthentifie = null;
			lUtilisateurAuthentifie = allowUser(auth);
			if (null != lUtilisateurAuthentifie) {
				StringBuilder lStrBuilder = new StringBuilder();
				lStrBuilder.append(lUtilisateurAuthentifie.getLogin());
				lStrBuilder.append(":");
				lStrBuilder.append(lUtilisateurAuthentifie.getMotPasse());
				String token = new String(Base64.encode(lStrBuilder.toString()
						.getBytes()));
				response.addHeader("Authorization", token);
				filterChain.doFilter(request, response);
			} else {
				throw new UnauthorizedException();
			}
		} catch (SQLException | GenericException e) {
			throw new UnauthorizedException();
		}
	}

	protected Utilisateur allowUser(String auth) throws IOException,
			SQLException, GenericException {
		if (auth == null) {
			return null;
		}
		if (!auth.toUpperCase().startsWith("BASIC "))
			return null; 
		String userpassEncoded = auth.substring(6);
		String[] loginEtMotDePasse = new String(Base64.decode(userpassEncoded))
				.split(":");
		if (2 != loginEtMotDePasse.length) {
			return null;
		}
		String lLogin = loginEtMotDePasse[0];
		String lMotDePasse = loginEtMotDePasse[1];
		Utilisateur lUtilisateurAAuthentifier = new Utilisateur();
		lUtilisateurAAuthentifier.setLogin(lLogin);
		lUtilisateurAAuthentifier.setMotPasse(lMotDePasse);
		UtilisateurServiceImpl lUtilisateurService = new UtilisateurServiceImpl(
				new UtilisateurDaoImpl(), new ProfilDaoImpl(),
				new DroitProfilDaoImpl());
		Utilisateur lUtilisateurAuthentifie = null;
		lUtilisateurAuthentifie = lUtilisateurService
					.authentifier(lUtilisateurAAuthentifier);
		return lUtilisateurAuthentifie;
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