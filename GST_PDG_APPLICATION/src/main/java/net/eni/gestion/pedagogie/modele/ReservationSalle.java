/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;
import net.sourceforge.jtds.jdbc.DateTime;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
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

	public final static String ID_FIELD_NAME 					= "RES_SALLE_ID";
	public final static String DATE_DEBUT_FIELD_NAME			= "RES_SALLE_DATE_DEBUT";
	public final static String DATE_FIN_FIELD_NAME				= "RES_SALLE_DATE_FIN";
	public final static String NB_POSTE_LIBRE_FIELD_NAME		= "RES_SALLE_NB_POSTE_LIBRE";
	public final static String SALLE_FIELD_NAME					= "RES_SALLE_SALLE";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = DATE_DEBUT_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateDebut = null;

	@DatabaseField(
		columnName = DATE_FIN_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateFin = null;

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
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}
