/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reinert.jjschema.SchemaIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.SESSION_VALIDATION_STAGIAIRE_TABLE_NAME)
@XmlRootElement
public class SessionValidationStagiaire extends AModele<Integer> implements Serializable {
	
	public SessionValidationStagiaire() {
		super();
	}

	public SessionValidationStagiaire(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 							= "SES_VAL_STG_ID";
	public final static String SESSION_VALIDATION_FIELD_NAME 			= "SES_VAL_STG_SESSION_VALIDATION";
	public final static String STAGIAIRE_FIELD_NAME 					= "SES_VAL_STG_STAGIAIRE";
	public final static String INSTANCE_SESSION_VALIDATION_FIELD_NAME	= "SES_VAL_STG_INSTANCE_SESSION_VALIDATION";
	public final static String DATE_HEURE_DEBUT_PASSAGE_FIELD_NAME		= "SES_VAL_STG_HEURE_DEBUT_PASSAGE";
	public final static String DATE_HEURE_FIN_FIELD_NAME				= "SES_VAL_STG_HEURE_FIN_PASSAGE";
	
	@SchemaIgnore
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@JsonBackReference("SessionValidationStagiaire-SessionValidation")
	@DatabaseField(
		columnName = SESSION_VALIDATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private SessionValidation sessionValidation = null;

	@SchemaIgnore
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false,
		foreignAutoRefresh = true)
	private StagiairePromotion stagiaire = null;

	@SchemaIgnore
	@DatabaseField(
			columnName = INSTANCE_SESSION_VALIDATION_FIELD_NAME,
			foreign = true,
			useGetSet = true,
			canBeNull = true)
		private InstanceSessionValidation instanceSessionValidation = null;

	@SchemaIgnore
	@JsonIgnore
	@DatabaseField(
		columnName = DATE_HEURE_DEBUT_PASSAGE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = true)
	private Date dateHeureDebut = null;

	@SchemaIgnore
	@JsonIgnore
	@DatabaseField(
		columnName = DATE_HEURE_FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = true)
	private Date dateHeureFin = null;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id=pId;
	}

	public SessionValidation getSessionValidation() {
		return sessionValidation;
	}

	public void setSessionValidation(SessionValidation sessionValidation) {
		this.sessionValidation = sessionValidation;
	}

	public StagiairePromotion getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(StagiairePromotion stagiaire) {
		this.stagiaire = stagiaire;
	}

	public InstanceSessionValidation getInstanceSessionValidation() {
		return instanceSessionValidation;
	}

	public void setInstanceSessionValidation(InstanceSessionValidation instanceSessionValidation) {
		this.instanceSessionValidation = instanceSessionValidation;
	}

	public Date getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(Date dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}

	public Date getDateHeureFin() {
		return dateHeureFin;
	}

	public void setDateHeureFin(Date dateHeureFin) {
		this.dateHeureFin = dateHeureFin;
	}
	

}
