package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import net.eni.gestion.pedagogie.DAO.AvisDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Avis;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Avis"
 */
@Singleton
public class AvisDaoImpl extends BaseDaoImpl<Avis, Integer> implements AvisDao{
	
	
	/**
	 * Constructeur de la DAO AvisBase
	 * @throws SQLException
	 */
	public AvisDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Avis.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Avis> charger(Avis pAvis) throws Exception {
		return CRUDHelper.charger(this, pAvis);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Avis)
	 */
	public Avis chargerDetail(Avis pAvis) throws Exception {
		return CRUDHelper.chargerDetail(this, pAvis);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Avis ajouter(Avis pAvis) throws Exception {
		return CRUDHelper.ajouter(this, pAvis);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Avis mettreAJour(Avis pAvis) throws Exception {
		return CRUDHelper.mettreAJour(this, pAvis);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Avis supprimer(Avis pAvis) throws Exception {
		return CRUDHelper.supprimer(this, pAvis);
	}

}
