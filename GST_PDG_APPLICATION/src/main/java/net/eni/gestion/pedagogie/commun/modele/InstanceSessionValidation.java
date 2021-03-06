/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	public final static String SESSION_VALIDATION_FIELD_NAME	= "INST_SES_VAL_SESSION_VALIDATION";
	public final static String RESERVATION_SALLE_FIELD_NAME		= "INST_SES_VAL_RESERVATION_SALLE";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@JsonBackReference("instance-session")
	@DatabaseField(
		columnName = SESSION_VALIDATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private SessionValidation sessionValidation = null;

	@JsonManagedReference("InstanceSessionValidation-ReservationSalle")
	@DatabaseField(
		columnName = RESERVATION_SALLE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		foreignAutoRefresh = true)
	private ReservationSalle reservationSalle = null;
	
	@JsonManagedReference("InstanceSessionValidation-Jury")
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

	public ArrayList<Jury> getJurys() {
		if (null != transientJurys) {
			jurys.clear();
			jurys.addAll(transientJurys);
			transientJurys = null;
		}
		return jurys;
	}
	
	
	public void setJurys(ArrayList<Jury> jurys) {
		this.jurys = jurys;
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
