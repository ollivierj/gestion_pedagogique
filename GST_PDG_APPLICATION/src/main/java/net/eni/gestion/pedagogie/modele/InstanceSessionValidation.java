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
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime date = null;
	
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


	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}
