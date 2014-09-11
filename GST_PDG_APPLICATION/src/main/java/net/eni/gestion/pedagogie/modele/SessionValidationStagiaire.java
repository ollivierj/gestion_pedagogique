/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.Date;

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
	public final static String DATE_HEURE_DEBUT_PASSAGE_FIELD_NAME		= "SES_VAL_STG_DATE_HEURE_DEBUT";
	public final static String DATE_HEURE_FIN_FIELD_NAME				= "SES_VAL_STG_DATE_HEURE_FIN";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = SESSION_VALIDATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private SessionValidation sessionValidation = null;

	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;

	@DatabaseField(
			columnName = INSTANCE_SESSION_VALIDATION_FIELD_NAME,
			foreign = true,
			useGetSet = true,
			canBeNull = false)
		private InstanceSessionValidation instanceSessionValidation = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_HEURE_DEBUT_PASSAGE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateHeureDebut = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_HEURE_FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
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

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
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
