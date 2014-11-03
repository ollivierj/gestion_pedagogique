package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.SessionValidationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.commun.outil.SearchCallable;
import net.eni.gestion.pedagogie.modele.SessionValidation;
import net.eni.gestion.pedagogie.modele.SessionValidationStagiaire;

import org.apache.commons.collections.CollectionUtils;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceSessionValidationStagiaire"
 */
@Singleton
public class SessionValidationStagiaireDaoImpl extends ADaoImpl<SessionValidationStagiaire, Integer> implements SessionValidationStagiaireDao{
	
	/**
	 * Constructeur de la DAO InstanceSessionValidationStagiaireBase
	 * @throws SQLException
	 */
	public SessionValidationStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), SessionValidationStagiaire.class);
	}
	
	public class findSessionValidationStagiaireByStagiaire implements SearchCallable<SessionValidationStagiaire,Integer> {
		SessionValidationStagiaire searchItem;
		ArrayList<SessionValidationStagiaire> itemList;
		
		public void findSessionValidationStagiaireByStgaire(){
		}
		
		@Override
		public void setSearchItem(SessionValidationStagiaire pSearchItem) {
			this.searchItem = pSearchItem;
		}

		@Override
		public void setItemList(
				ArrayList<SessionValidationStagiaire> pItemList) {
			this.itemList = pItemList;
		}

		@Override
		public SessionValidationStagiaire call() throws Exception {
			final SessionValidationStagiaire pFinalSearchItem = this.searchItem;
			return (SessionValidationStagiaire) CollectionUtils.find(this.itemList,
					new org.apache.commons.collections.Predicate() {
						public boolean evaluate(Object object) {
							return ((SessionValidationStagiaire) object).getStagiaire().getId() == pFinalSearchItem.getStagiaire().getId();
						}
					});
		}
	}
	
	public ArrayList<SessionValidationStagiaire> mettreAJourCollectionStagiaireForSessionValidation(
			SessionValidation pSessionValidation,
			ArrayList<SessionValidationStagiaire> pSessionValidationStagiaires) throws Exception {
		return CRUDHelper.mettreAJourCollection(this, pSessionValidation, pSessionValidationStagiaires, SessionValidationStagiaire.STAGIAIRE_FIELD_NAME, new findSessionValidationStagiaireByStagiaire());
	}

}