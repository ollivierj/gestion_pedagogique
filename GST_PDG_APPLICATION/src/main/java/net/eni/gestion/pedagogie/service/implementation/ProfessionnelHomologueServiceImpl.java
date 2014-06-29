package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.ProfessionnelHomologue;
import net.eni.gestion.pedagogie.service.ProfessionnelHomologueService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des professionnel homologues (membres de jury potentiels)
 */
@Singleton
public class ProfessionnelHomologueServiceImpl implements ProfessionnelHomologueService {

    /**
     * DAO professionnelHomologue
     */
    private final ProfessionnelHomologueDao professionnelHomologueDao;

    /**
     * Constructeur
     * @param DAO professionnelHomologue
     * @throws SQLException
     */
    @Inject
    public ProfessionnelHomologueServiceImpl(ProfessionnelHomologueDao pProfessionnelHomologueDao) throws SQLException {
        this.professionnelHomologueDao = pProfessionnelHomologueDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<ProfessionnelHomologue> charger(ProfessionnelHomologue pProfessionnelHomologue)
			throws GenericException {
		try {
			return this.professionnelHomologueDao.charger(pProfessionnelHomologue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public ProfessionnelHomologue chargerDetail(ProfessionnelHomologue pModel) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.professionnelHomologueDao.chargerDetail(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ProfessionnelHomologue ajouter(ProfessionnelHomologue pModel) throws GenericException {
		try {
			return this.professionnelHomologueDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ProfessionnelHomologue mettreAJour(ProfessionnelHomologue pModel) throws GenericException {
		try {
			return this.professionnelHomologueDao.mettreAJour(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ProfessionnelHomologue supprimer(ProfessionnelHomologue pModel) throws GenericException {
		try {
			return this.professionnelHomologueDao.supprimer(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
