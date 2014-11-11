package net.eni.gestion.pedagogie.DAO;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.DroitProfil;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des droitprofils
 */
public interface DroitProfilDao extends ADao<DroitProfil, Integer> {
	public abstract ArrayList<String> getListeDroits(Integer pProfilId)
			throws ApplicationException;

	public abstract ArrayList<String> mettreAJourDroits(Integer pProfilId,
			ArrayList<String> lListeDroits) throws ApplicationException;
	
	public void deleteDroits(Integer pProfilId) throws ApplicationException;

}
