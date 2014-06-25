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
@DatabaseTable(tableName = ModeleMetier.PROFESSIONNEL_HOMOLOGUE_TABLE_NAME)
@XmlRootElement
public class ProfessionnelHomologue extends AModele<Integer> implements Serializable {
	
	public ProfessionnelHomologue() {
		super();
	}

	public ProfessionnelHomologue(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "PRF_HMG_ID";
	public final static String CIVILITE_FIELD_NAME 				= "PRF_HMG_CIVILITE";
	public final static String NOM_FIELD_NAME					= "PRF_HMG_NOM";
	public final static String PRENOM_FIELD_NAME 				= "PRF_HMG_PRENOM";
	public final static String ADRESSE1_FIELD_NAME	 			= "PRF_HMG_ADRESSE1";
	public final static String ADRESSE2_FIELD_NAME	 			= "PRF_HMG_ADRESSE2";
	public final static String ADRESSE3_FIELD_NAME	 			= "PRF_HMG_ADRESSE3";
	public final static String CODE_POSTAL_FIELD_NAME	 		= "PRF_HMG_CODE_POSTAL";
	public final static String VILLE_FIELD_NAME	 				= "PRF_HMG_VILLE";
	public final static String TELEPHONE_FIXE_FIELD_NAME	 	= "PRF_HMG_TELEPHONE_FIXE";
	public final static String TELEPHONE_PORTABLE_FIELD_NAME	= "PRF_HMG_TELEPHONE_PORTABLE";
	public final static String EMAIL_FIELD_NAME	 				= "PRF_HMG_EMAIL";
	public final static String DATE_NAISSANCE_FIELD_NAME	 	= "PRF_HMG_DATE_NAISSANCE";
	public final static String CODE_REGION_FIELD_NAME	 		= "PRF_HMG_CODE_REGION";
	public final static String CODE_NATIONALITE_FIELD_NAME	 		= "PRF_HMG_NATIONALITE";
	public final static String PERMIS_FIELD_NAME	 			= "PRF_HMG_PERMIS";
	public final static String PHOTO_FIELD_NAME	 				= "PRF_HMG_PHOTO";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = CIVILITE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String civilite = null;

	@DatabaseField(
		columnName = NOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String nom = null;

	@DatabaseField(
		columnName = PRENOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String prenom = null;

	@DatabaseField(
		columnName = ADRESSE1_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String adresse1 = null;

	@DatabaseField(
		columnName = ADRESSE2_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String adresse2 = null;

	@DatabaseField(
		columnName = ADRESSE3_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String adresse3 = null;

	@DatabaseField(
		columnName = CODE_POSTAL_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codePostal = null;

	@DatabaseField(
		columnName = VILLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String ville = null;

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
		useGetSet = true)
	private String email = null;

	@DatabaseField(
		columnName = DATE_NAISSANCE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private DateTime dateNaissance = null;

	@DatabaseField(
		columnName = CODE_REGION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeRegion = null;

	@DatabaseField(
		columnName = CODE_NATIONALITE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeNationalite = null;

	@DatabaseField(
		columnName = PERMIS_FIELD_NAME,
		dataType = DataType.BOOLEAN,
		useGetSet = true)
	private Boolean permis = null;
	
	@DatabaseField(
		columnName = PHOTO_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String photo = null;
		
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}
