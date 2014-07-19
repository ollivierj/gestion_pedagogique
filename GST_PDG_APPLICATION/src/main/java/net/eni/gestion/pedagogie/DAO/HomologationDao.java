package net.eni.gestion.pedagogie.DAO;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.modele.Homologation;
import net.eni.gestion.pedagogie.modele.ProfessionnelHomologue;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des homologations
 */
public interface HomologationDao extends ADao<Homologation, Integer> {
	
	ArrayList<Homologation> mettreAJourCollectionHomologationForProfessionnelHomologue(ProfessionnelHomologue pProfessionnelHomologue, ArrayList<Homologation> pHomologations) throws Exception;
		

}
