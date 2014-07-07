/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.PLANNING_INDIVIDUEL_DETAIL_TABLE_NAME)
@XmlRootElement
public class PlanningIndividuelDetail extends AModele<String> implements Serializable {
	
	public PlanningIndividuelDetail() {
		super();
	}

	public PlanningIndividuelDetail(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID1_FIELD_NAME 						= "CodePlanning";
	public final static String ID2_FIELD_NAME 						= "IdCours";
	public final static String PRIX_COURS_DEVIS_FIELD_NAME			= "PrixCoursDevis";
	public final static String PRIX_COURS_PEC_DEVIS_FIELD_NAME 		= "PrixCoursPECDevis";
	public final static String PRIX_COURS_FINANCE_DEVIS_FIELD_NAME 	= "PrixCoursPECDevis";
	public final static String DISPENSE_FIELD_NAME 					= "Dispense";
	public final static String INSCRIT_FIELD_NAME 					= "Inscrit";
	public final static String DEBUT_COURS_FIELD_NAME 				= "DebutCours";
	public final static String FIN_COURS_FIELD_NAME 				= "FinCours";
	public final static String HEURES_REELLES_COURS_FIELD_NAME 		= "HeuresReellesCours";

	@DatabaseField(
		columnName = ID1_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private PlanningIndividuelFormation planningIndividuelFormation = null;
	
	@DatabaseField(
		columnName = ID2_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Cours cours = null;
	
	@DatabaseField(
		columnName = PRIX_COURS_DEVIS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixCoursDevis = null;

	@DatabaseField(
		columnName = PRIX_COURS_PEC_DEVIS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true)
	private Float prixCoursPECDevis = null;

	@DatabaseField(
		columnName = PRIX_COURS_FINANCE_DEVIS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true)
	private Float prixCoursFinanceDevis = null;

	@DatabaseField(
		columnName = DISPENSE_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean dispense = null;
	
	@DatabaseField(
		columnName = INSCRIT_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean Inscrit = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DEBUT_COURS_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date debutCours = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = FIN_COURS_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date finCours = null;

	@DatabaseField(
		columnName = HEURES_REELLES_COURS_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short heuresRellesCours = null;
		
	@Override
	public String getId() {
		return String.valueOf(planningIndividuelFormation.getId()) + "/" + String.valueOf(cours.getId());
	}

	@Override
	public void setId(String pId) {
		String[] ids = pId.split("/");;
		setPlanningIndividuelFormation(new PlanningIndividuelFormation(Integer.valueOf(ids[0])));
		setCours(new Cours(UUID.fromString(ids[1])));
	}

	public PlanningIndividuelFormation getPlanningIndividuelFormation() {
		return planningIndividuelFormation;
	}

	public void setPlanningIndividuelFormation(PlanningIndividuelFormation pPlanningIndividuelFormation) {
		this.planningIndividuelFormation = pPlanningIndividuelFormation;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours pCours) {
		this.cours = pCours;
	}

	public Float getPrixCoursDevis() {
		return prixCoursDevis;
	}

	public void setPrixCoursDevis(Float prixCoursDevis) {
		this.prixCoursDevis = prixCoursDevis;
	}

	public Float getPrixCoursPECDevis() {
		return prixCoursPECDevis;
	}

	public void setPrixCoursPECDevis(Float prixCoursPECDevis) {
		this.prixCoursPECDevis = prixCoursPECDevis;
	}

	public Float getPrixCoursFinanceDevis() {
		return prixCoursFinanceDevis;
	}

	public void setPrixCoursFinanceDevis(Float prixCoursFinanceDevis) {
		this.prixCoursFinanceDevis = prixCoursFinanceDevis;
	}

	public Boolean getDispense() {
		return dispense;
	}

	public void setDispense(Boolean dispense) {
		this.dispense = dispense;
	}

	public Boolean getInscrit() {
		return Inscrit;
	}

	public void setInscrit(Boolean inscrit) {
		Inscrit = inscrit;
	}

	public Date getDebutCours() {
		return debutCours;
	}
	
	public void setDebutCours(Date debutCours) {
		this.debutCours = debutCours;
	}

	public Date getFinCours() {
		return finCours;
	}
	
	public void setFinCours(Date finCours) {
		this.finCours = finCours;
	}

	public Short getHeuresRellesCours() {
		return heuresRellesCours;
	}

	public void setHeuresRellesCours(Short heuresRellesCours) {
		this.heuresRellesCours = heuresRellesCours;
	} 

}
