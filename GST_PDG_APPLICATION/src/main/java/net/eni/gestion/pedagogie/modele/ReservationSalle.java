/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

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
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = DATE_DEBUT_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateDebut = null;

	@DatabaseField(
		columnName = DATE_FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateFin = null;

	@DatabaseField(
		columnName = NB_POSTE_LIBRE_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true)
	private Integer nbPosteLibre = null;

	@DatabaseField(
		columnName = SALLE_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Salle salle = null;

	@Override
	public Integer getId() {
		return id;
	}

	@ForeignCollectionField(eager = true, columnName = InstanceCours.RESERVATION_SALLE_FIELD_NAME)
	private transient Collection<InstanceCours> transientInstanceCours = null;

	private InstanceCours instanceCours = null;
	
	@ForeignCollectionField(eager = true, columnName = InstanceEvaluation.RESERVATION_SALLE_FIELD_NAME)
	private transient Collection<InstanceEvaluation> transientInstanceEvaluations = null;

	private InstanceEvaluation instanceEvaluation = null;
	
	@ForeignCollectionField(eager = true, columnName = InstanceSessionValidation.RESERVATION_SALLE_FIELD_NAME)
	private transient Collection<InstanceSessionValidation> transientInstanceSessionValidations = null;

	private InstanceSessionValidation instanceSessionValidation = null;
	
	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Date getDateDebut() {
		return dateDebut;
	}
	
	public String getFormatedDateDebut(){
		return (null!=dateDebut)? DateFormatUtils.format(dateDebut, "dd/MM/yyyy H:mm:ss"): null;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}
	public String getFormatedDateFin(){
		return (null!=dateFin)? DateFormatUtils.format(dateFin, "dd/MM/yyyy H:mm:ss"): null;
	}
	
	
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
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
	}

}
