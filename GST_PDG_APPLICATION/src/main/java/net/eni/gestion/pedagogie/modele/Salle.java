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
@DatabaseTable(tableName = ModeleMetier.SALLE_TABLE_NAME)
@XmlRootElement
public class Salle extends AModele<Integer> implements Serializable {
	
	public Salle() {
		super();
	}

	public Salle(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 		= "SALLE_ID";
	public final static String LIBELLE_FIELD_NAME	= "LIBELLE";
	public final static String NBPLACE_FIELD_NAME	= "NB_PLACES";
	public final static String LIEU_FIELD_NAME		= "LIEU";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;
	
	@DatabaseField(
		columnName = NBPLACE_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer nbPlaces = null;
	
	@DatabaseField(
		columnName = LIEU_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lieu = null;
	
	/*
	@JsonManagedReference("reservation-salle")
	@ForeignCollectionField(eager = true, columnName = ReservationSalle.SALLE_FIELD_NAME)
	private transient Collection<ReservationSalle> transientReservationSalles = null;

	private ArrayList<ReservationSalle> reservationSalles = new ArrayList<ReservationSalle>();
	*/
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/*
	public ArrayList<ReservationSalle> getReservationSalles() {
		if (null != transientReservationSalles) {
			reservationSalles.clear();
			reservationSalles.addAll(transientReservationSalles);
			transientReservationSalles = null;
		}
		return reservationSalles;
	}
	*/

	public Integer getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

}
