package net.eni.gestion.pedagogie.DAO.implementation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import net.eni.gestion.pedagogie.DAO.ADao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

abstract class ADaoImpl<M extends AModele<ID>, ID> extends BaseDaoImpl<M,ID> implements ADao<M, ID>{

	protected ADaoImpl(ConnectionSource connectionSource, Class<M> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Pair<ArrayList<M>, Long> charger(Pager pPager) throws Exception {
		return CRUDHelper.charger(this, pPager);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.ADao#chargerForAutocompleteSearch(java.lang.String)
	 */
	public ArrayList<M> chargerForAutocompleteSearch(String pSearchText) throws Exception {
		return CRUDHelper.chargerForAutocompleteSearch(this, pSearchText);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Avis)
	 */
	public M chargerDetail(ID pId) throws Exception {
		return CRUDHelper.chargerDetail(this, pId);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M ajouter(M pMember) throws Exception {
		if (this.validerAvantajout(pMember)){
			return CRUDHelper.ajouter(this, pMember);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M mettreAJour(M pMember) throws Exception {
		if (this.validerAvantMiseAJour(pMember)){
			return CRUDHelper.mettreAJour(this, pMember);
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ID supprimer(ID pId) throws Exception {
		if (this.validerAvantSuppression(pId)){
			return CRUDHelper.supprimer(this, pId);
		}
		return null;
	}
	
	public HashMap<String, String> getTitleMap() throws Exception {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	protected boolean validerAvantajout(M pMember) throws ApplicationException {
		return true;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	protected boolean validerAvantMiseAJour(M pMember) throws ApplicationException {
		return true;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	protected boolean validerAvantSuppression(ID pId) throws ApplicationException {
		return true;
	}
	
	public ArrayList<M> chargerTous() throws Exception {
		return CRUDHelper.chargerTous(this);
	}
	
	public M addOrUpdate(M pModel) throws Exception {
		try {
			M m = this.chargerDetail(pModel.getId());
			// Si le modèle n'existe pas en base on fait un ajout...
			if (m == null) {
				return this.ajouter(pModel);
			// ... Sinon on le met à jour
			} else {
				return this.mettreAJour(pModel);
			}
		} catch (Exception e) {
			throw new ApplicationException("Echec lors de la mise à jour en base de données.");
		}
	}
}
