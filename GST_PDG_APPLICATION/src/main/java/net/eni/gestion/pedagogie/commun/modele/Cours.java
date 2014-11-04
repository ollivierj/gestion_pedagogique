/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.COURS_TABLE_NAME)
@XmlRootElement
public class Cours extends AModele<UUID> implements Serializable {
	
	public Cours() {
		super();
	}

	public Cours(UUID pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "IdCours";
	public final static String DEBUT_FIELD_NAME 					= "Debut";
	public final static String FIN_FIELD_NAME						= "Fin";
	public final static String DUREE_REELLE_EN_HEURES_FIELD_NAME	= "DureeReelleEnHeures";
	public final static String CODE_PROMOTION_FIELD_NAME 			= "CodePromotion";
	public final static String PRIX_PUBLIC_AFFECTE_FIELD_NAME 		= "PrixPublicAffecte";
	public final static String DATE_CREATION_FIELD_NAME 			= "DateCreation";
	public final static String DATE_MODIF_FIELD_NAME 				= "DateModif";
	public final static String ID_MODULE_FIELD_NAME 				= "IdModule";
	public final static String LIBELLE_COURS_FIELD_NAME 			= "LibelleCours";
	public final static String DUREE_PREVUE_EN_HEURES_FIELD_NAME 	= "DureePrevueEnHeures";
	public final static String CODE_SALLE_FIELD_NAME 				= "CodeSalle";
	public final static String CODE_FORMATEUR_FIELD_NAME 			= "CodeFormateur";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.UUID,
		id = true,
		useGetSet = true)
	private UUID id = null;
	
	@DatabaseField(
		columnName = DEBUT_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date debut = null;

	@DatabaseField(
		columnName = FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date fin = null;

	@DatabaseField(
		columnName = DUREE_REELLE_EN_HEURES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeReelleEnHeures = null;

	@DatabaseField(
		columnName = CODE_PROMOTION_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Promotion promotion = null;
	
	@DatabaseField(
		columnName = PRIX_PUBLIC_AFFECTE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPublicAffecte = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateCreation = null;

//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
//	@DatabaseField(
//		columnName = DATE_MODIF_FIELD_NAME,
//		dataType = DataType.DATE,
//		useGetSet = true,
//		canBeNull = false)
//	private Date datesModif = null;

	@DatabaseField(
		columnName = ID_MODULE_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer idModule = null;

	@DatabaseField(
		columnName = LIBELLE_COURS_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelleCours = null;

	@DatabaseField(
		columnName = DUREE_PREVUE_EN_HEURES_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer dureePrevueEnHeures = null;

	//@JsonManagedReference("cours-planning")
//	@ForeignCollectionField(eager = false, columnName = PlanningIndividuelDetail.ID2_FIELD_NAME)
//	private transient Collection<PlanningIndividuelDetail> transientPlanningIndividuelDetails = null;
//
//	private ArrayList<PlanningIndividuelDetail> planningIndividuelDetails = new ArrayList<PlanningIndividuelDetail>();

	@ForeignCollectionField(eager = false, columnName = InstanceCours.COURS_FIELD_NAME)
	private transient Collection<InstanceCours> transientInstanceCours = null;

	private ArrayList<InstanceCours> instanceCours = new ArrayList<InstanceCours>();

	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setId(UUID pId) {
		id = pId;
	}

	public Date getDebut() {
		return debut;
	}
	
	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Short getDureeReelleEnHeures() {
		return dureeReelleEnHeures;
	}

	public void setDureeReelleEnHeures(Short dureeReelleEnHeures) {
		this.dureeReelleEnHeures = dureeReelleEnHeures;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Float getPrixPublicAffecte() {
		return prixPublicAffecte;
	}

	public void setPrixPublicAffecte(Float prixPublicAffecte) {
		this.prixPublicAffecte = prixPublicAffecte;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

//	public Date getDateModif() {
//		return dateModif;
//	}
//
//	public void setDateModif(Date dateModif) {
//		this.dateModif = dateModif;
//	}

	public Integer getIdModule() {
		return idModule;
	}

	public void setIdModule(Integer idModule) {
		this.idModule = idModule;
	}

	public String getLibelleCours() {
		return libelleCours;
	}

	public void setLibelleCours(String libelleCours) {
		this.libelleCours = libelleCours;
	}

	public Integer getDureePrevueEnHeures() {
		return dureePrevueEnHeures;
	}

	public void setDureePrevueEnHeures(Integer dureePrevueEnHeures) {
		this.dureePrevueEnHeures = dureePrevueEnHeures;
	}
	
//	private ArrayList<PlanningIndividuelDetail> getPlanningIndividuelDetails() {
//		if (null != transientPlanningIndividuelDetails) {
//			planningIndividuelDetails.clear();
//			planningIndividuelDetails.addAll(transientPlanningIndividuelDetails);
//			transientPlanningIndividuelDetails = null;
//		}
//		return planningIndividuelDetails;
//	}
//	
//	public ArrayList<Stagiaire> getStagiaires(){
//		ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
//		if (0 < getPlanningIndividuelDetails().size()) {
//			Iterator<PlanningIndividuelDetail> iterator = getPlanningIndividuelDetails().iterator();
//			while (iterator.hasNext()) {
//				stagiaires.add(iterator.next().getPlanningIndividuelFormation().getStagiaire());
//			}
//		}
//		return stagiaires;
//	}
	
	public ArrayList<InstanceCours> getInstanceCours() {
		if (null != transientInstanceCours) {
			instanceCours.clear();
			instanceCours.addAll(transientInstanceCours);
			transientInstanceCours = null;
		}
		return instanceCours;
	}
	


}