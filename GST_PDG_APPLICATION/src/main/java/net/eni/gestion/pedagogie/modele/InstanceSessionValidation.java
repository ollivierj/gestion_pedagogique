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
@DatabaseTable(tableName = ModeleMetier.INSTANCE_SESSION_VALIDATION_TABLE_NAME)
@XmlRootElement
public class InstanceSessionValidation extends AModele<Integer> implements Serializable {
	
	public InstanceSessionValidation() {
		super();
	}

	public InstanceSessionValidation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "INST_SES_VAL_ID";
	public final static String SESSION_VALIDATION_FIELD_NAME	= "INST_SES_SESSION_VALIDATION";
	public final static String RESERVATION_SALLE_FIELD_NAME		= "INST_SES_RESERVATION_SALLE";
	public final static String DATE_FIELD_NAME					= "INST_SES_DATE";
	public final static String LIEN_DOCS_GENERES_FIELD_NAME		= "INST_SES_LIEN_DOCS_GENERES";
	public final static String LIEN_DOCS_COLLECTES_FIELD_NAME	= "INST_SES_LIEN_DOCS_COLLECTES";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = SESSION_VALIDATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private SessionValidation sessionValidation = null;

	@DatabaseField(
		columnName = RESERVATION_SALLE_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private ReservationSalle reservationSalle = null;

	@DatabaseField(
		columnName = DATE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date date= null;
	
	@DatabaseField(
		columnName = LIEN_DOCS_GENERES_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String lienDocsGeneres = null;

	@DatabaseField(
		columnName = LIEN_DOCS_COLLECTES_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String lienDocsCollectes = null;

	@ForeignCollectionField(eager = true, columnName = InstanceSessionValidationStagiaire.INSTANCE_SESSION_VALIDATION_FIELD_NAME)
	private transient Collection<InstanceSessionValidationStagiaire> transientInstanceSessionValidationStagiaires = null;

	private ArrayList<InstanceSessionValidationStagiaire> instanceSessionValidationStagiaires = new ArrayList<InstanceSessionValidationStagiaire>();

	@ForeignCollectionField(eager = true, columnName = Jury.INSTANCE_SESSION_VALIDATION_FIELD_NAME)
	private transient Collection<Jury> transientJurys = null;

	private ArrayList<Jury> jurys = new ArrayList<Jury>();

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public SessionValidation getSessionValidation() {
		return sessionValidation;
	}

	public void setSessionValidation(SessionValidation sessionValidation) {
		this.sessionValidation = sessionValidation;
	}

	public ReservationSalle getReservationSalle() {
		return reservationSalle;
	}

	public void setReservationSalle(ReservationSalle reservationSalle) {
		this.reservationSalle = reservationSalle;
	}

	public Date getDate() {
		return date;
	}

	public String getFormatedDate(){
		return (null!=date)? DateFormatUtils.format(date, "dd/MM/yyyy"): null;
	}
	
	public void setDate(Date date) {
		this.date= date;
	}

	public String getLienDocsGeneres() {
		return lienDocsGeneres;
	}

	public void setLienDocsGeneres(String lienDocsGeneres) {
		this.lienDocsGeneres = lienDocsGeneres;
	}

	public String getLienDocsCollectes() {
		return lienDocsCollectes;
	}

	public void setLienDocsCollectes(String lienDocsCollectes) {
		this.lienDocsCollectes = lienDocsCollectes;
	}
	
	public ArrayList<InstanceSessionValidationStagiaire> getInstanceSessionValidationStagiaires() {
		if (null != transientInstanceSessionValidationStagiaires) {
			instanceSessionValidationStagiaires.clear();
			instanceSessionValidationStagiaires.addAll(transientInstanceSessionValidationStagiaires);
			transientInstanceSessionValidationStagiaires = null;
		}
		return instanceSessionValidationStagiaires;
	}
	
	public ArrayList<Stagiaire> getStagiaires(){
		ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
		if (0 < getInstanceSessionValidationStagiaires().size()) {
			Iterator<InstanceSessionValidationStagiaire> iterator = getInstanceSessionValidationStagiaires().iterator();
			while (iterator.hasNext()) {
				stagiaires.add(iterator.next().getStagiaire());
			}
		}
		return stagiaires;
	}
	
	private ArrayList<Jury> getJurys() {
		if (null != transientJurys) {
			jurys.clear();
			jurys.addAll(transientJurys);
			transientJurys = null;
		}
		return jurys;
	}
	
	public ArrayList<ProfessionnelHomologue> getJures(){
		ArrayList<ProfessionnelHomologue> jures = new ArrayList<ProfessionnelHomologue>();
		if (0 < getJurys().size()) {
			Iterator<Jury> iterator = getJurys().iterator();
			while (iterator.hasNext()) {
				jures.add(iterator.next().getProfessionnelHomologue());
			}
		}
		return jures;
	}

}
