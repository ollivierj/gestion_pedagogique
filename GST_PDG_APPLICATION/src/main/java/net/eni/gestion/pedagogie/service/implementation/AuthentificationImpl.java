package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.DroitDao;
import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.DAO.implementation.DroitDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ProfilDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.UtilisateurDaoImpl;
import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.service.Authentification;

public class AuthentificationImpl implements Authentification {

	@Override
	public Utilisateur isAuthenticated(String authorization) {
		
		String[] authenInfo = authorization.split(";");
		Utilisateur util = new Utilisateur();
		util.setLogin(authenInfo[0]);
		util.setMotPasse(authenInfo[1]);
		
		UtilisateurDao pUtilisateurDao;
		try {
			pUtilisateurDao = new UtilisateurDaoImpl();
			ProfilDao profilDao = new ProfilDaoImpl();
			DroitDao droitDao = new DroitDaoImpl();
			UtilisateurServiceImpl userServ = new UtilisateurServiceImpl(pUtilisateurDao, profilDao, droitDao);
			return userServ.checkLogin(util);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
