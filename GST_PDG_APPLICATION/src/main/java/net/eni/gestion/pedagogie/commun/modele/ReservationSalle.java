/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;
import net.eni.gestion.pedagogie.commun.outil.DateHelper;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.RESERVATION_SALLE_TABLE_NAME)
@XmlRootElement
public class ReservationSalle extends AModele<Integer> implements Serializable {
	
	public ReservationSalle() {
		super();
	}

	public ReservationSalle(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 				= "RES_SALLE_ID";
	public final static String DATE_DEBUT_FIELD_NAME		= "RES_SALLE_DATE_DEBUT";
	public final static String DATE_FIN_FIELD_NAME			= "RES_SALLE_DATE_FIN";
	public final static String NB_POSTE_LIBRE_FIELD_NAME	= "RES_SALLE_NB_POSTE_LIBRE";
	public final static String SALLE_FIELD_NAME				= "RES_SALLE_SALLE";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_DEBUT_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateDebut = null;
	
	private String formatedDateDebut = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateFin = null;
	
	private String formatedDateFin = null;

	@DatabaseField(
		columnName = NB_POSTE_LIBRE_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer nbPosteLibre = null;

	//@JsonBackReference("reservation-salle")
	@DatabaseField(
		columnName = SALLE_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Salle salle = null;

	@Override
	public Integer getId() {
		return id;
	}

	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = InstanceCours.RESERVATION_SALLE_FIELD_NAME)
	private transient Collection<InstanceCours> transientInstanceCours = null;

	@JsonBackReference("InstanceCours-ReservationSalle")
	private InstanceCours instanceCours = null;
	
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = InstanceEvaluation.RESERVATION_SALLE_FIELD_NAME)
	private transient Collection<InstanceEvaluation> transientInstanceEvaluations = null;

	@JsonBackReference("InstanceEvaluation-ReservationSalle")
	private InstanceEvaluation instanceEvaluation = null;
	
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = InstanceSessionValidation.RESERVATION_SALLE_FIELD_NAME)
	private transient Collection<InstanceSessionValidation> transientInstanceSessionValidations = null;

	@JsonBackReference("InstanceSessionValidation-ReservationSalle")
	private InstanceSessionValidation instanceSessionValidation = null;
	
	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Date getDateDebut() {
		return dateDebut;
	}
	
	public void setDateDebut(Date dateDebut) {
		this.formatedDateDebut=DateHelper.stringifyDate(dateDebut, "yyyy-MM-dd'T'HH:mm:ss");
		this.dateDebut = dateDebut;
	}
	
	public String getFormatedDateDebut() {
		return formatedDateDebut;
	}

	public void setFormatedDateDebut(String formatedDateDebut) throws ParseException {
		this.dateDebut=DateHelper.datifyString(formatedDateDebut, "yyyy-MM-dd'T'HH:mm:ss");
		this.formatedDateDebut = formatedDateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}
	
	public void setDateFin(Date dateFin) {
		this.formatedDateFin=DateHelper.stringifyDate(dateFin, "yyyy-MM-dd'T'HH:mm:ss");
		this.dateFin = dateFin;
	}
	
	public String getFormatedDateFin() {
		return formatedDateFin;
	}

	public void setFormatedDateFin(String formatedDateFin) throws ParseException {
		this.dateFin=DateHelper.datifyString(formatedDateFin, "yyyy-MM-dd'T'HH:mm:ss");
		this.formatedDateFin = formatedDateFin;
	}

	public Integer getNbPosteLibre() {
		return nbPosteLibre;
	}

	public void setNbPosteLibre(Integer nbPosteLibre) {
		this.nbPosteLibre = nbPosteLibre;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	/*
	public InstanceCours getInstanceCours() {
		if (null != transientInstanceCours && 1 == transientInstanceCours.size()) {
			instanceCours = transientInstanceCours.iterator().next();
			transientInstanceCours = null;
		}
		return instanceCours;
	}
	
	public InstanceEvaluation getInstanceEvaluation() {
		if (null != transientInstanceEvaluations && 1 == transientInstanceEvaluations.size()) {
			instanceEvaluation = transientInstanceEvaluations.iterator().next();
			transientInstanceEvaluations = null;
		}
		return instanceEvaluation;
	}
	
	public InstanceSessionValidation getInstanceSessionValidation() {
		if (null != transientInstanceSessionValidations && 1 == transientInstanceSessionValidations.size()) {
			instanceSessionValidation = transientInstanceSessionValidations.iterator().next();
			transientInstanceSessionValidations = null;
		}
		return instanceSessionValidation;
	}*/

}
