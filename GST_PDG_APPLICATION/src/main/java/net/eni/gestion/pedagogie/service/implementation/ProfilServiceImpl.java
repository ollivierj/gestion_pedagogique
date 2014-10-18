package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.DroitProfilDao;
import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Profil;
import net.eni.gestion.pedagogie.service.ProfilService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des profils
 */
@Singleton
public class ProfilServiceImpl extends AServiceImpl<Profil, Integer, ProfilDao> implements ProfilService {

	
	protected final DroitProfilDao droitProfilDao;
       /**
     * Constructeur
     * @param DAO profil
     * @throws SQLException
     */
    @Inject
    public ProfilServiceImpl(ProfilDao pProfilDao, DroitProfilDao pDroitProfilDao) throws SQLException {
        super(pProfilDao);
        this.droitProfilDao = pDroitProfilDao;
    }

	@Override
	public Profil chargerDetail(Integer pId) throws GenericException {
		Profil lProfil = super.chargerDetail(pId);
		try {
			lProfil.setDroits(droitProfilDao.getListeDroits(pId));
		} catch (SQLException e) {
			throw new GenericException(
					"Echec lors de la mise à jour en base de données.");
		}
		return lProfil;
	}

	@Override
	public Profil ajouter(Profil pModel) throws GenericException {
		ArrayList<String> lDroits = pModel.getDroits();
		Profil lProfil = super.ajouter(pModel);
		try {
			lProfil.setDroits(this.droitProfilDao.mettreAJourDroits(lProfil.getId(), lDroits));
			return lProfil;
		} catch (Exception e) {
			throw new GenericException(
					"Echec lors de la mise à jour en base de données.");
		}
	}

	@Override
	public Profil mettreAJour(Profil pModel) throws GenericException {
		ArrayList<String> lDroits = pModel.getDroits();
		Profil lProfil = super.mettreAJour(pModel);
		try {
			lProfil.setDroits(this.droitProfilDao.mettreAJourDroits(lProfil.getId(), lDroits));
			return lProfil;
		} catch (Exception e) {
			throw new GenericException(
					"Echec lors de la mise à jour en base de données.");
		}
	}

	@Override
	public Integer supprimer(Integer pId) throws GenericException {
		try {
			this.droitProfilDao.deleteDroits(pId);
			return super.supprimer(pId);
		} catch (SQLException e) {
			throw new GenericException(
					"Echec lors de la mise à jour en base de données.");
		}
	}
    
}
