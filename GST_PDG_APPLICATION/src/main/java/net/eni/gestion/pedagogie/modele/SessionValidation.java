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
@DatabaseTable(tableName = ModeleMetier.SESSION_VALIDATION_TABLE_NAME)
@XmlRootElement
public class SessionValidation extends AModele<Integer> implements Serializable {
	
	public SessionValidation() {
		super();
	}

	public SessionValidation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "SES_VAL_ID";
	public final static String AUTEUR_FIELD_NAME 					= "SES_VAL_AUTEUR";
	public final static String DATE_CREATION_FIELD_NAME 			= "SES_VAL_DATE_CREATION";
	public final static String TITRE_PROFESSIONNEL_FIELD_NAME		= "SES_VAL_TITRE_PROFESSIONNEL";
	public final static String DATE_DEBUT_FIELD_NAME				= "SES_VAL_DATE_DEBUT";
	public final static String DATE_FIN_FIELD_NAME					= "SES_VAL_DATE_FIN";
	public final static String LIEN_MODELES_PUBLIPOSTAGE_FIELD_NAME	= "SES_VAL_LIEN_MODELES_PUBLIPOSTAGE";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;

	@DatabaseField(
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur auteur = null;
	
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateCreation = null;

	@DatabaseField(
		columnName = TITRE_PROFESSIONNEL_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private TitreProfessionnel titreProfessionnel = null;

	@DatabaseField(
		columnName = DATE_DEBUT_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateDebut = null;

	@DatabaseField(
		columnName = DATE_FIN_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateFin = null;
	
	@DatabaseField(
		columnName = LIEN_MODELES_PUBLIPOSTAGE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lienModelesPublipostage = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}
