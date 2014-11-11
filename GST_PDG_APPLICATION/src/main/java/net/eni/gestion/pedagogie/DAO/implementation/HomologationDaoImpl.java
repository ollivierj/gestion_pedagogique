package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.HomologationDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Homologation;
import net.eni.gestion.pedagogie.commun.modele.ProfessionnelHomologue;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.commun.outil.SearchCallable;

import org.apache.commons.collections.CollectionUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

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
	@Inject
	public HomologationDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, Homologation.class);
	}

	public ArrayList<Homologation> mettreAJourCollectionHomologationForProfessionnelHomologue(
			ProfessionnelHomologue pProfessionnelHomologue,
			ArrayList<Homologation> pHomologations) throws ApplicationException {
		return CRUDHelper.mettreAJourCollection(this, pProfessionnelHomologue, pHomologations, Homologation.PROFESSIONNEL_HOMOLOGUE_FIELD_NAME, new findHomologationByProfessionnelHomologue());
	}	
	
	public class findHomologationByProfessionnelHomologue implements SearchCallable<Homologation,Integer> {
		Homologation searchItem;
		ArrayList<Homologation> itemList;
		
		public findHomologationByProfessionnelHomologue(){
		}
		
		@Override
		public void setSearchItem(Homologation pSearchItem) {
			this.searchItem = pSearchItem;
		}

		@Override
		public void setItemList(
				ArrayList<Homologation> pItemList) {
			this.itemList = pItemList;
		}

		@Override
		public Homologation call() throws ApplicationException {
			final Homologation pFinalSearchItem = this.searchItem;
			return (Homologation) CollectionUtils.find(this.itemList,
					new org.apache.commons.collections.Predicate() {
						public boolean evaluate(Object object) {
							return ((Homologation) object).getId() == pFinalSearchItem.getId();
						}
					});
		}


	}

}
