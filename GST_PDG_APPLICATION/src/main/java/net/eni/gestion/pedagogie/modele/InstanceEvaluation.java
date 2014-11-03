/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.INSTANCE_EVALUATION_TABLE_NAME)
@XmlRootElement
public class InstanceEvaluation extends AModele<Integer> implements Serializable {
	
	public InstanceEvaluation() {
		super();
	}

	public InstanceEvaluation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "INST_EVAL_ID";
	public final static String RESERVATION_SALLE_FIELD_NAME 		= "INST_EVAL_RESERVATION_SALLE";
	public final static String SURVEILLANT_FIELD_NAME 				= "INST_EVAL_SURVEILLANT";
	public final static String EVALUATION_FIELD_NAME 				= "INST_EVAL_EVALUATION";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = RESERVATION_SALLE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private ReservationSalle reservationSalle = null;

	@DatabaseField(
		columnName = SURVEILLANT_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur surveillant = null;
	
	@DatabaseField(
		columnName = EVALUATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Evaluation evaluation = null;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public ReservationSalle getReservationSalle() {
		return reservationSalle;
	}

	public void setReservationSalle(ReservationSalle reservationSalle) {
		this.reservationSalle = reservationSalle;
	}

	public Utilisateur getSurveillant() {
		return surveillant;
	}

	public void setSurveillant(Utilisateur surveillant) {
		this.surveillant = surveillant;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

}
