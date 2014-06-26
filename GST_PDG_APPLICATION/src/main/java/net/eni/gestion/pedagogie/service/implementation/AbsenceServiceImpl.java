package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Absence;
import net.eni.gestion.pedagogie.service.AbsenceService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des absences
 */
@Singleton
public class AbsenceServiceImpl implements AbsenceService {

    /**
     * DAO absence
     */
    private final AbsenceDao absenceDao;

    /**
     * Constructeur
     * @param DAO absence
     * @throws SQLException
     */
    @Inject
    public AbsenceServiceImpl(AbsenceDao pAbsenceDao) throws SQLException {
        this.absenceDao = pAbsenceDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Absence> charger(Absence pModel)
			throws GenericException {
		try {
			return this.absenceDao.charger(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public Absence chargerDetail(Absence pModel) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.absenceDao.chargerDetail(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Absence ajouter(Absence pModel) throws GenericException {
		try {
			return this.absenceDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Absence mettreAJour(Absence pModel) throws GenericException {
		try {
			return this.absenceDao.mettreAJour(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Absence supprimer(Absence pModel) throws GenericException {
		try {
			return this.absenceDao.supprimer(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
