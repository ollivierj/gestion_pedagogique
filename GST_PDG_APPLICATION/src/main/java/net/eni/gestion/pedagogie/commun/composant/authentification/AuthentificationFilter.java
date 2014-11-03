package net.eni.gestion.pedagogie.commun.composant.authentification;

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
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationExceptionMapper;
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
			if (allowUser(auth)) {
				response.addHeader("Authorization", auth);
				filterChain.doFilter(request, response);
			} else {
				throw new ApplicationException("Vous devez vous connecter");
			}
		} catch (ApplicationException e) {
			new ApplicationExceptionMapper().toResponse(e);
		} catch (SQLException e) {
			new ApplicationExceptionMapper().toResponse(new ApplicationException("Erreur en base de données"));
		}
	}

	protected boolean allowUser(String auth) throws IOException,
			SQLException, ApplicationException {
		if (auth == null || !auth.toUpperCase().startsWith("BASIC ")) {
			return false;
		}
		String userpassEncoded = auth.substring(6);
		String[] loginEtMotDePasse = new String(Base64.decode(userpassEncoded)).split(":");
		if (2 != loginEtMotDePasse.length) {
			return false;
		}
		String lLogin = loginEtMotDePasse[0];
		String lMotDePasse = loginEtMotDePasse[1];
		Utilisateur lUtilisateurAAuthentifier = new Utilisateur();
		lUtilisateurAAuthentifier.setLogin(lLogin);
		lUtilisateurAAuthentifier.setMotPasse(lMotDePasse);
		UtilisateurServiceImpl lUtilisateurService = 
			new UtilisateurServiceImpl(
				new UtilisateurDaoImpl(), new ProfilDaoImpl(),
				new DroitProfilDaoImpl()
			);
		return lUtilisateurService.checkConnection(lUtilisateurAAuthentifier, false);
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