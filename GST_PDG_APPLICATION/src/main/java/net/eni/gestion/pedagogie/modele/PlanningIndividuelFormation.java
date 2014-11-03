/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.PLANNING_INDIVIDUEL_FORMATION_TABLE_NAME)
@XmlRootElement
public class PlanningIndividuelFormation extends AModele<Integer> implements Serializable {
	
	public PlanningIndividuelFormation() {
		super();
	}

	public PlanningIndividuelFormation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 				= "CodePlanning";
	public final static String CODE_STAGIAIRE_FIELD_NAME 	= "CodeStagiaire";
	public final static String DATE_INSCRIPTION_FIELD_NAME	= "DateInscription";
	public final static String DATE_CREATION_FIELD_NAME 	= "DateCreation";
	public final static String CODE_TYPE_PROFIL_FIELD_NAME	= "CodeTypeProfil";
	public final static String CODE_FORMATION_FIELD_NAME 	= "CodeFormation";
	public final static String CODE_PROMOTION_FIELD_NAME 	= "CodePromotion";
	public final static String DATE_MODIF_FIELD_NAME 		= "DateModif";
	public final static String NUM_LIEN_FIELD_NAME	 		= "NumLien";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = CODE_STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")   
	@DatabaseField(
		columnName = DATE_INSCRIPTION_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date dateInscription = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")   
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date dateCreation = null;

	@DatabaseField(
		columnName = CODE_TYPE_PROFIL_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer codeTypeProfil = null;

	@DatabaseField(
		columnName = CODE_FORMATION_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Formation formation = null;
	
	@DatabaseField(
		columnName = CODE_PROMOTION_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Promotion promotion = null;

	@DatabaseField(
		columnName = NUM_LIEN_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true)
	private Integer numLien = null;
	
	@ForeignCollectionField(eager = true, columnName = PlanningIndividuelDetail.ID1_FIELD_NAME)
	private transient Collection<PlanningIndividuelDetail> transientPlanningIndividuelDetails = null;

	private ArrayList<PlanningIndividuelDetail> planningIndividuelDetails = new ArrayList<PlanningIndividuelDetail>();

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Date getDateInscription() {
		return dateInscription;
	}
	
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Integer getCodeTypeProfil() {
		return codeTypeProfil;
	}

	public void setCodeTypeProfil(Integer codeTypeProfil) {
		this.codeTypeProfil = codeTypeProfil;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Integer getNumLien() {
		return numLien;
	}

	public void setNumLien(Integer numLien) {
		this.numLien = numLien;
	}

	private ArrayList<PlanningIndividuelDetail> getPlanningIndividuelDetails() {
		if (null != transientPlanningIndividuelDetails) {
			planningIndividuelDetails.clear();
			planningIndividuelDetails.addAll(transientPlanningIndividuelDetails);
			transientPlanningIndividuelDetails = null;
		}
		return planningIndividuelDetails;
	}
	
	public ArrayList<Cours> getCours(){
		ArrayList<Cours> cours = new ArrayList<Cours>();
		if (0 < getPlanningIndividuelDetails().size()) {
			Iterator<PlanningIndividuelDetail> iterator = getPlanningIndividuelDetails().iterator();
			while (iterator.hasNext()) {
				cours.add(iterator.next().getCours());
			}
		}
		return cours;
	}

}
