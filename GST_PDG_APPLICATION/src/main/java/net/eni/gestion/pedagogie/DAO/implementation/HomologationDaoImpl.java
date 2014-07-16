package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.HomologationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.ORMLiteHelper;
import net.eni.gestion.pedagogie.modele.Homologation;
import net.eni.gestion.pedagogie.modele.ProfessionnelHomologue;

import com.google.inject.Singleton;

/**
 * @author jollivier Service métier "Homologation"
 */
@Singleton
public class HomologationDaoImpl extends ADaoImpl<Homologation, Integer>
		implements HomologationDao {

	/**
	 * Constructeur de la DAO HomologationBase
	 * 
	 * @throws SQLException
	 */
	public HomologationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Homologation.class);
	}

	@SuppressWarnings("unused")
	public ArrayList<Homologation> mettreAJourCollectionHomologationForProfessionnelHomologue(
			ProfessionnelHomologue pProfessionnelHomologue,
			ArrayList<Homologation> pHomologations) throws Exception {
		ArrayList<Homologation> lHomologationsEnBase = null;
		try {

			lHomologationsEnBase = new ArrayList<Homologation>(this.queryForEq(
					Homologation.PROFESSIONNEL_HOMOLOGUE_FIELD_NAME,
					pProfessionnelHomologue.getId()));
			if (null != lHomologationsEnBase) {
				for (Homologation lHomologationEnBase : lHomologationsEnBase) {
					// L'homologation référencée précédemment est-elle absente
					// des nouvelles homologations référencées ?
					if (null == ORMLiteHelper.findItemInList(lHomologationEnBase, pHomologations)) {
						// Si oui, suppression de l'ancienne homologation référencée
						this.delete(lHomologationEnBase);
					}
				}
				// Pour toutes les homologations à sauvegarder en base ....
				for (Homologation lHomologation : pHomologations) {
					// Et on on sauvegarde
					if (null != lHomologation.getDateFin() && null != lHomologation.getDateFin() && null != lHomologation.getTitreProfessionnel() && null != lHomologation.getProfessionnelHomologue()){
						this.createOrUpdate(lHomologation);
					}
					
				}
				return pHomologations;
			}
		} catch (Exception e) {
			throw new Exception(
					"Echec de mise à jour des associations des homologations du professionnel homologués");
		}
		return null;
		
	}



}
