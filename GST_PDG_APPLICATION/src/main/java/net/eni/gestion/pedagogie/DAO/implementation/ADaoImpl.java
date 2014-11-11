package net.eni.gestion.pedagogie.DAO.implementation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import net.eni.gestion.pedagogie.DAO.ADao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;

import com.j256.ormlite.dao.BaseDaoImpl;

abstract class ADaoImpl<M extends AModele<ID>, ID> extends BaseDaoImpl<M,ID> implements ADao<M, ID>{

	public Connexion connexion; 
	
	protected ADaoImpl(Class<M> dataClass, Connexion pConnexion) throws SQLException {
		super(pConnexion.getConnection(), dataClass);
		connexion = pConnexion;
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Pair<ArrayList<M>, Long> charger(Pager pPager) throws ApplicationException {
		return CRUDHelper.charger(this, pPager);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.ADao#chargerForAutocompleteSearch(java.lang.String)
	 */
	public ArrayList<M> chargerForAutocompleteSearch(String pSearchText) throws ApplicationException {
		return CRUDHelper.chargerForAutocompleteSearch(this, pSearchText);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Avis)
	 */
	public M chargerDetail(ID pId) throws ApplicationException {
		return CRUDHelper.chargerDetail(this, pId);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M ajouter(M pMember) throws ApplicationException {
		if (this.validerAvantajout(pMember)){
			return CRUDHelper.ajouter(this, pMember);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M mettreAJour(M pMember) throws ApplicationException {
		if (this.validerAvantMiseAJour(pMember)){
			return CRUDHelper.mettreAJour(this, pMember);
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ID supprimer(ID pId) throws ApplicationException {
		if (this.validerAvantSuppression(pId)){
			return CRUDHelper.supprimer(this, pId);
		}
		return null;
	}
	
	public HashMap<String, String> getTitleMap() throws ApplicationException {
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
	
	public ArrayList<M> chargerTous() throws ApplicationException {
		return CRUDHelper.chargerTous(this);
	}
	
	public M addOrUpdate(M pModel) throws ApplicationException {
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
