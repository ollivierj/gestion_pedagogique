package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import net.eni.gestion.pedagogie.DAO.EchangeDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Echange;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Echange"
 */
@Singleton
public class EchangeDaoImpl extends BaseDaoImpl<Echange, Integer> implements EchangeDao{
	
	
	/**
	 * Constructeur de la DAO EchangeBase
	 * @throws SQLException
	 */
	public EchangeDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Echange.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Echange> charger(Echange pEchange) throws Exception {
		return CRUDHelper.charger(this, pEchange);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Echange)
	 */
	public Echange chargerDetail(Echange pEchange) throws Exception {
		return CRUDHelper.chargerDetail(this, pEchange);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Echange ajouter(Echange pEchange) throws Exception {
		return CRUDHelper.ajouter(this, pEchange);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Echange mettreAJour(Echange pEchange) throws Exception {
		return CRUDHelper.mettreAJour(this, pEchange);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Echange supprimer(Echange pEchange) throws Exception {
		return CRUDHelper.supprimer(this, pEchange);
	}

}
