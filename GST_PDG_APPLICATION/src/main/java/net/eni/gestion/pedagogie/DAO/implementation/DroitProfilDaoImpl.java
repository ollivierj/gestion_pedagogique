package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.DroitProfilDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Droit;
import net.eni.gestion.pedagogie.modele.DroitProfil;
import net.eni.gestion.pedagogie.modele.Profil;

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
		super(Connexion.getConnexion(), DroitProfil.class);
	}
	
	public void deleteDroits(Integer pProfilId) throws SQLException{
		DeleteBuilder<DroitProfil, Integer> lDeleteBuilder= this.deleteBuilder();
		lDeleteBuilder.where().eq(DroitProfil.PROFIL_FIELD_NAME, pProfilId);
		lDeleteBuilder.delete();
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.implementation.test#getListeDroits(java.lang.Integer)
	 */
	@Override
	public ArrayList<String> getListeDroits(Integer pProfilId) throws SQLException{
		ArrayList<DroitProfil> lDroitProfilArray = new ArrayList<DroitProfil>(this.queryBuilder().where().eq(DroitProfil.PROFIL_FIELD_NAME, pProfilId).query());
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
	public ArrayList<String> mettreAJourDroits(Integer pProfilId, ArrayList<String> lListeDroits) throws Exception {
		ArrayList<String> lListeDroitsEnBase = null;
		try {
			lListeDroitsEnBase=this.getListeDroits(pProfilId);
			Droit lRefentielDroit = null;
			DroitDaoImpl lDroitDao = new DroitDaoImpl();
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
			throw new Exception(
					"Echec de mise à jour des associations des homologations du professionnel homologués");
		}
		
	}
	
	

	
}
