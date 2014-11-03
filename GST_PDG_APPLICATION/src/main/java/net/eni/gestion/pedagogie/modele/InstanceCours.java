/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.INSTANCE_COURS_TABLE_NAME)
@XmlRootElement
public class InstanceCours extends AModele<Integer> implements Serializable {

	public InstanceCours() {
		super();
	}

	public InstanceCours(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 				= "INST_COURS_ID";
	public final static String ANIMATEUR_FIELD_NAME 		= "INST_COURS_ANIMATEUR";
	public final static String RESERVATION_SALLE_FIELD_NAME = "INST_COURS_RESERVATION_SALLE";
	public final static String COURS_FIELD_NAME 			= "INST_COURS_COURS";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = ANIMATEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur animateur = null;

	@DatabaseField(
		columnName = RESERVATION_SALLE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private ReservationSalle reservationSalle = null;

	@DatabaseField(
		columnName = COURS_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false,
		foreignAutoRefresh = true)
	private Cours cours = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}
	
	public Utilisateur getAnimateur() {
		return animateur;
	}

	public void setAnimateur(Utilisateur animateur) {
		this.animateur = animateur;
	}

	public ReservationSalle getReservationSalle() {
		return reservationSalle;
	}

	public void setReservationSalle(ReservationSalle reservationSalle) {
		this.reservationSalle = reservationSalle;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

}
