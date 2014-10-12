package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.EvaluationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.commun.outil.SearchCallable;
import net.eni.gestion.pedagogie.modele.Evaluation;
import net.eni.gestion.pedagogie.modele.EvaluationStagiaire;

import org.apache.commons.collections.CollectionUtils;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceEvaluationStagiaire"
 */
@Singleton
public class EvaluationStagiaireDaoImpl extends ADaoImpl<EvaluationStagiaire, Integer> implements EvaluationStagiaireDao{
	
	/**
	 * Constructeur de la DAO InstanceEvaluationStagiaireBase
	 * @throws SQLException
	 */
	public EvaluationStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), EvaluationStagiaire.class);
	}
	
	public class findEvaluationStagiaireByStagiaire implements SearchCallable<EvaluationStagiaire,Integer> {
		EvaluationStagiaire searchItem;
		ArrayList<EvaluationStagiaire> itemList;
		
		public void findEvaluationStagiaireByStgaire(){
		}
		
		@Override
		public void setSearchItem(EvaluationStagiaire pSearchItem) {
			this.searchItem = pSearchItem;
		}

		@Override
		public void setItemList(
				ArrayList<EvaluationStagiaire> pItemList) {
			this.itemList = pItemList;
		}

		@Override
		public EvaluationStagiaire call() throws Exception {
			final EvaluationStagiaire pFinalSearchItem = this.searchItem;
			return (EvaluationStagiaire) CollectionUtils.find(this.itemList,
					new org.apache.commons.collections.Predicate() {
						public boolean evaluate(Object object) {
							return ((EvaluationStagiaire) object).getStagiaire().getId() == pFinalSearchItem.getStagiaire().getId();
						}
					});
		}


	}

	public ArrayList<EvaluationStagiaire> mettreAJourCollectionStagiaireForEvaluation(
			Evaluation pEvaluation,
			ArrayList<EvaluationStagiaire> pEvaluationStagiaires) throws Exception {
		return CRUDHelper.mettreAJourCollection(this, pEvaluation, pEvaluationStagiaires, EvaluationStagiaire.STAGIAIRE_FIELD_NAME, new findEvaluationStagiaireByStagiaire());
	}

}
