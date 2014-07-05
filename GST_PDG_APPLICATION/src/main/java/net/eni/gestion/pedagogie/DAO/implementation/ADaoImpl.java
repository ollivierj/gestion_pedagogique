package net.eni.gestion.pedagogie.DAO.implementation;
import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.ADao;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.generique.AModele;

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
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Avis)
	 */
	public M chargerDetail(ID pId) throws Exception {
		return CRUDHelper.chargerDetail(this, pId);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M ajouter(M pMember) throws Exception {
		return CRUDHelper.ajouter(this, pMember);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M mettreAJour(M pMember) throws Exception {
		return CRUDHelper.mettreAJour(this, pMember);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ID supprimer(ID pId) throws Exception {
		return CRUDHelper.supprimer(this, pId);
	}


}
