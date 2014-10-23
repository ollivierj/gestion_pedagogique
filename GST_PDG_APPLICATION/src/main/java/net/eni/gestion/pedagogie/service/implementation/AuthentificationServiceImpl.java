package net.eni.gestion.pedagogie.service.implementation;

import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.service.Authentification;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;

public class AuthentificationServiceImpl implements Authentification {

	protected final UtilisateurService utilisateurService;
	
	@Inject
	public AuthentificationServiceImpl(UtilisateurService pUtilisateurService) {
		super();
		this.utilisateurService = pUtilisateurService;
	}
	
	@Override
	public Utilisateur isAuthenticated(String authorization) {
		String[] authenInfo = authorization.split(";");
		Utilisateur util = new Utilisateur();
		util.setLogin(authenInfo[0]);
		util.setMotPasse(authenInfo[1]);
		try {
			return utilisateurService.checkLogin(util);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
