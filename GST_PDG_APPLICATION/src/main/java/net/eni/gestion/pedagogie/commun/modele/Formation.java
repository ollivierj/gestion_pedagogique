/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.FORMATION_TABLE_NAME)
@XmlRootElement
public class Formation extends AModele<String> implements Serializable {
	
	public Formation() {
		super();
	}

	public Formation(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "CodeFormation";
	public final static String LIBELLE_LONG_FIELD_NAME 			= "LibelleLong";
	public final static String LIBELLE_COURT_FIELD_NAME 		= "LibelleCourt";
	public final static String DUREE_EN_HEURES_FIELD_NAME 		= "DureeEnHeures";
	public final static String TAUX_HORAIRE_FIELD_NAME 			= "TauxHoraire";
	public final static String DATE_CREATION_FIELD_NAME 		= "DateCreation";
	public final static String CODE_TITRE_FIELD_NAME 			= "CodeTitre";
	public final static String PRIX_PUBLIC_EN_COURS_FIELD_NAME 	= "PrixPublicEnCours";
	public final static String HEURES_CENTRE_FIELD_NAME 		= "HeuresCentre";
	public final static String HEURES_STAGE_FIELD_NAME 			= "HeuresStage";
	public final static String SEMAINES_CENTRE_FIELD_NAME 		= "SemainesCentre";
	public final static String SEMAINES_STAGE_FIELD_NAME 		= "SemainesStage";
	public final static String DUREE_EN_SEMAINES_FIELD_NAME 	= "DureeEnSemaines";
	public final static String DATE_MODIF_FIELD_NAME 			= "DateModif";
	public final static String ARCHIVER_FIELD_NAME 				= "Archiver";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.STRING,
		id = true,
		useGetSet = true)
	private String id = null;
	
	@DatabaseField(
		columnName = LIBELLE_LONG_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelleLong = null;

	@DatabaseField(
		columnName = LIBELLE_COURT_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelleCourt = null;
	
	@DatabaseField(
		columnName = DUREE_EN_HEURES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeEnHeures = null;

	@DatabaseField(
		columnName = TAUX_HORAIRE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float tauxHoraire = null;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateCreation = null;
	
	@DatabaseField(
		columnName = CODE_TITRE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeTitre = null;
	
	@DatabaseField(
		columnName = PRIX_PUBLIC_EN_COURS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPublicEnCours = null;
	
	@DatabaseField(
		columnName = HEURES_CENTRE_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short heuresCentre = null;

	@DatabaseField(
		columnName = HEURES_STAGE_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short heuresStage = null;
	
	@DatabaseField(
		columnName = SEMAINES_CENTRE_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short semainesCentre = null;

	@DatabaseField(
		columnName = SEMAINES_STAGE_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short semainesStage = null;
	
	@DatabaseField(
		columnName = DUREE_EN_SEMAINES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeEnSemaines = null;

	@DatabaseField(
		columnName = ARCHIVER_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean archiver = null;
	
	@ForeignCollectionField(eager = true, columnName = UniteParFormation.CODE_FORMATION_FIELD_NAME)
	private transient Collection<UniteParFormation> transientUniteParFormations = null;

	private ArrayList<UniteParFormation> uniteParFormations = new ArrayList<UniteParFormation>();
	
	@ForeignCollectionField(eager = true, columnName = PlanningIndividuelFormation.CODE_FORMATION_FIELD_NAME)
	private transient Collection<PlanningIndividuelFormation> transientPlanningIndividuelFormations = null;

	private ArrayList<PlanningIndividuelFormation> planningIndividuelFormations = new ArrayList<PlanningIndividuelFormation>();

	@ForeignCollectionField(eager = true, columnName = Promotion.CODE_FORMATION_FIELD_NAME)
	private transient Collection<Promotion> transientPromotions = null;

	private ArrayList<Promotion> promotions = new ArrayList<Promotion>();
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String pId) {
		id = pId;
	}

	public String getLibelleLong() {
		return libelleLong;
	}

	public void setLibelleLong(String libelleLong) {
		this.libelleLong = libelleLong;
	}

	public String getLibelleCourt() {
		return libelleCourt;
	}

	public void setLibelleCourt(String libelleCourt) {
		this.libelleCourt = libelleCourt;
	}

	public Short getDureeEnHeures() {
		return dureeEnHeures;
	}

	public void setDureeEnHeures(Short dureeEnHeures) {
		this.dureeEnHeures = dureeEnHeures;
	}

	public Float getTauxHoraire() {
		return tauxHoraire;
	}

	public void setTauxHoraire(Float tauxHoraire) {
		this.tauxHoraire = tauxHoraire;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	
	public String getFormatedDateCreation(){
		return (null!=dateCreation)? DateFormatUtils.format(dateCreation, "dd/MM/yyyy H:mm:ss"): null;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getCodeTitre() {
		return codeTitre;
	}

	public void setCodeTitre(String codeTitre) {
		this.codeTitre = codeTitre;
	}

	public Float getPrixPublicEnCours() {
		return prixPublicEnCours;
	}

	public void setPrixPublicEnCours(Float prixPublicEnCours) {
		this.prixPublicEnCours = prixPublicEnCours;
	}

	public Short getHeuresCentre() {
		return heuresCentre;
	}

	public void setHeuresCentre(Short heuresCentre) {
		this.heuresCentre = heuresCentre;
	}

	public Short getHeuresStage() {
		return heuresStage;
	}

	public void setHeuresStage(Short heuresStage) {
		this.heuresStage = heuresStage;
	}

	public Short getSemainesCentre() {
		return semainesCentre;
	}

	public void setSemainesCentre(Short semainesCentre) {
		this.semainesCentre = semainesCentre;
	}

	public Short getSemainesStage() {
		return semainesStage;
	}

	public void setSemainesStage(Short semainesStage) {
		this.semainesStage = semainesStage;
	}

	public Short getDureeEnSemaines() {
		return dureeEnSemaines;
	}

	public void setDureeEnSemaines(Short dureeEnSemaines) {
		this.dureeEnSemaines = dureeEnSemaines;
	}

	public Boolean getArchiver() {
		return archiver;
	}

	public void setArchiver(Boolean archiver) {
		this.archiver = archiver;
	}
	
	public ArrayList<UniteParFormation> getUniteParFormations() {
		if (null != transientUniteParFormations) {
			uniteParFormations.clear();
			uniteParFormations.addAll(transientUniteParFormations);
			transientUniteParFormations = null;
		}
		return uniteParFormations;
	}
	
	private ArrayList<PlanningIndividuelFormation> getPlanningIndividuelFormations() {
		if (null != transientPlanningIndividuelFormations) {
			planningIndividuelFormations.clear();
			planningIndividuelFormations.addAll(transientPlanningIndividuelFormations);
			transientPlanningIndividuelFormations = null;
		}
		return planningIndividuelFormations;
	}
	
	public ArrayList<Stagiaire> getStagiaires(){
		ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
		if (0 < getPlanningIndividuelFormations().size()) {
			Iterator<PlanningIndividuelFormation> iterator = getPlanningIndividuelFormations().iterator();
			while (iterator.hasNext()) {
				stagiaires.add(iterator.next().getStagiaire());
			}
		}
		return stagiaires;
	}
		
	public ArrayList<Promotion> getPromotions() {
		if (null != transientPromotions) {
			promotions.clear();
			promotions.addAll(transientPromotions);
			transientPromotions = null;
		}
		return promotions;
	}

}
