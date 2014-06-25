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
@DatabaseTable(tableName = ModeleMetier.UTILISATEUR_TABLE_NAME)
@XmlRootElement
public class Utilisateur extends AModele<Integer> implements Serializable {
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 				= "UTIL_ID";
	public final static String FONCTION_FIELD_NAME			= "UTIL_FONCTION";
	public final static String CIVILITE_FIELD_NAME			= "UTIL_CIVILITE";
	public final static String NOM_FIELD_NAME				= "UTIL_NOM";
	public final static String PRENOM_FIELD_NAME			= "UTIL_PRENOM";
	public final static String TELEPHONE_FIXE_FIELD_NAME	= "UTIL_TELEPHONE_FIXE";
	public final static String TELEPHONE_PORTABLE_FIELD_NAME= "UTIL_TELEPHONE_PORTABLE";
	public final static String EMAIL_FIELD_NAME				= "UTIL_EMAIl";
	public final static String PHOTO_FIELD_NAME				= "UTIL_PHOTO";
	public final static String MOT_PASSE_FIELD_NAME			= "UTIL_MOT_PASSE";
	public final static String PROFIL_FIELD_NAME			= "UTIL_PROFIL";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = FONCTION_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Fonction fonction = null;

	@DatabaseField(
		columnName = CIVILITE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String civilite = null;

	@DatabaseField(
		columnName = NOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String nom = null;

	@DatabaseField(
		columnName = PRENOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String prenom = null;

	@DatabaseField(
		columnName = TELEPHONE_FIXE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String telephoneFixe = null;

	@DatabaseField(
		columnName = TELEPHONE_PORTABLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String telephonePortable = null;

	@DatabaseField(
		columnName = EMAIL_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String email = null;

	@DatabaseField(
		columnName = PHOTO_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String photo = null;
	
	@DatabaseField(
		columnName = MOT_PASSE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String motPasse = null;

	@DatabaseField(
		columnName = PROFIL_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer profil = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}
