package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.DroitProfilDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Droit;
import net.eni.gestion.pedagogie.commun.modele.DroitProfil;
import net.eni.gestion.pedagogie.commun.modele.Profil;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.google.inject.Singleton;
import com.j256.ormlite.stmt.DeleteBuilder;

/**
 * @author jollivier
 * Service métier "DroitProfil"
 */
@Singleton
public class DroitProfilDaoImpl extends ADaoImpl<DroitProfil, Integer> implements DroitProfilDao{
	
	/**
	 * Constructeur de la DAO DroitProfilBase
	 * @throws SQLException
	 */
	public DroitProfilDaoImpl() throws SQLException {
		super(DroitProfil.class);
	}
	
	public void deleteDroits(Integer pProfilId) throws ApplicationException{
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.UTILISATEUR_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(Utilisateur.PROFIL_FIELD_NAME);
		lQuery.append("=");
		lQuery.append(pProfilId);
		String[] instanceExist;
		try {
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		if (null==instanceExist){
			DeleteBuilder<DroitProfil, Integer> lDeleteBuilder= this.deleteBuilder();
			try {
				lDeleteBuilder.where().eq(DroitProfil.PROFIL_FIELD_NAME, pProfilId);
				lDeleteBuilder.delete();
			} catch (SQLException e) {
				throw new ApplicationException("Echec lors de la suppression des droits en base de données");
			}
		}else {
			throw new ApplicationException("Il existe au moins un utilisateur pour ce profil.\n Vous devez vous assurer que le profil n'est pas utilisé par un utilisateur");
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.implementation.test#getListeDroits(java.lang.Integer)
	 */
	@Override
	public ArrayList<String> getListeDroits(Integer pProfilId) throws ApplicationException{
		ArrayList<DroitProfil> lDroitProfilArray;
		try {
			lDroitProfilArray = new ArrayList<DroitProfil>(this.queryBuilder().where().eq(DroitProfil.PROFIL_FIELD_NAME, pProfilId).query());
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la récupération des droits en base de données");
		}
		ArrayList<String> lDroits = new ArrayList<String>();
		DroitProfil lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals(Droit.GROUPE_STAGIAIRE);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals(Droit.GROUPE_EVALUATION);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals(Droit.GROUPE_SESSION_VALIDATION);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals( Droit.GROUPE_RESERVATION_SALLE);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals( Droit.GROUPE_ABSENCE);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals( Droit.GROUPE_ECHANGE);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals( Droit.GROUPE_AVIS);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals( Droit.GROUPE_UTILISATEUR);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals( Droit.GROUPE_PROFIL);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals( Droit.GROUPE_SUJET_EVALUATION);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals( Droit.GROUPE_PROFESSIONNEL_HOMOLOGUE);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		lDroitProfil = null;
		lDroitProfil = (DroitProfil) CollectionUtils.find(lDroitProfilArray,
				new Predicate() {
					public boolean evaluate(Object object) {
						return ((DroitProfil) object).getDroit().getGroupe().equals( Droit.GROUPE_TITRE_PROFESSIONNEL);
					}
				});
		if (null==lDroitProfil){return null;};
		lDroits.add(lDroitProfil.getDroit().getCode());
		return lDroits;
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.implementation.test#mettreAJourDroits(java.lang.Integer, java.util.ArrayList)
	 */
	@Override
	public ArrayList<String> mettreAJourDroits(Integer pProfilId, ArrayList<String> lListeDroits) throws ApplicationException {
		ArrayList<String> lListeDroitsEnBase = null;
		try {
			lListeDroitsEnBase=this.getListeDroits(pProfilId);
			Droit lRefentielDroit = null;
			DroitDaoImpl lDroitDao = new DroitDaoImpl(this.getConnectionSource());
			if (null != lListeDroitsEnBase && 12 == lListeDroitsEnBase.size() && lListeDroitsEnBase.size()==lListeDroits.size()) {
				for (int i=0; i<lListeDroits.size();i++){
					String lDroitEnBase = lListeDroitsEnBase.get(i);
					String lDroit = lListeDroits.get(i);
					if (lDroitEnBase != lDroit){
						lRefentielDroit = lDroitDao.queryForFirst(lDroitDao.queryBuilder().where().eq(Droit.CODE_FIELD_NAME, lDroit).prepare());
						Droit lRefentielDroitEnBase = lDroitDao.queryForFirst(lDroitDao.queryBuilder().where().eq(Droit.CODE_FIELD_NAME, lDroitEnBase).prepare());
						DroitProfil lDroitProfilAModifier = this.queryForFirst(this.queryBuilder().where().eq(DroitProfil.DROIT_FIELD_NAME, lRefentielDroitEnBase.getId()).and().eq(DroitProfil.PROFIL_FIELD_NAME, pProfilId).prepare());
						lDroitProfilAModifier.setDroit(lRefentielDroit);
						this.createOrUpdate(lDroitProfilAModifier);
					}
				}
			}else {
				DeleteBuilder<DroitProfil, Integer> lDeleteBuilder= this.deleteBuilder();
				lDeleteBuilder.where().eq(DroitProfil.PROFIL_FIELD_NAME, pProfilId);
				lDeleteBuilder.delete();
				ProfilDaoImpl lProfilDao = new ProfilDaoImpl();
				Profil lReferentielProfil = lProfilDao.queryForId(pProfilId);
				if (null!=lReferentielProfil){
					for (int i=0; i<lListeDroits.size();i++){
						String lDroit = lListeDroits.get(i);
						lRefentielDroit = lDroitDao.queryForFirst(lDroitDao.queryBuilder().where().eq(Droit.CODE_FIELD_NAME, lDroit).prepare());
						if (null != lRefentielDroit){
							DroitProfil lNouveauDroitProfil = new DroitProfil();
							lNouveauDroitProfil.setDroit(lRefentielDroit);
							lNouveauDroitProfil.setProfil(lReferentielProfil);
							this.createOrUpdate(lNouveauDroitProfil);
						}
					}
				}
				
			}
			return this.getListeDroits(pProfilId);
		} catch (Exception e) {
			throw new ApplicationException(
					"Echec de mise à jour des associations des homologations du professionnel homologués");
		}
		
	}
	
	
	

	
}
