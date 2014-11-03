/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.SALLE_TABLE_NAME)
@XmlRootElement
public class Salle extends AModele<String> implements Serializable {
	
	public Salle() {
		super();
	}

	public Salle(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 		= "CodeSalle";
	public final static String LIBELLE_FIELD_NAME	= "Libelle";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.STRING,
		id = true,
		useGetSet = true)
	private String id = null;
	
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;
	
	@ForeignCollectionField(eager = true, columnName = ReservationSalle.SALLE_FIELD_NAME)
	private transient Collection<ReservationSalle> transientReservationSalles = null;

	private ArrayList<ReservationSalle> reservationSalles = new ArrayList<ReservationSalle>();

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String pId) {
		id = pId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ArrayList<ReservationSalle> getReservationSalles() {
		if (null != transientReservationSalles) {
			reservationSalles.clear();
			reservationSalles.addAll(transientReservationSalles);
			transientReservationSalles = null;
		}
		return reservationSalles;
	}

}
