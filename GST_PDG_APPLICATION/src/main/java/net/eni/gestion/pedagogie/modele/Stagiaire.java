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
@DatabaseTable(tableName = ModeleMetier.STAGIAIRE_TABLE_NAME)
@XmlRootElement
public class Stagiaire extends AModele<Integer> implements Serializable {
	
	public Stagiaire() {
		super();
	}

	public Stagiaire(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "CodeStagiaire";
	public final static String CIVILITE_FIELD_NAME 				= "Civilite";
	public final static String NOM_FIELD_NAME					= "Nom";
	public final static String PRENOM_FIELD_NAME 				= "Prenom";
	public final static String ADRESSE1_FIELD_NAME	 			= "Adresse1";
	public final static String ADRESSE2_FIELD_NAME	 			= "Adresse2";
	public final static String ADRESSE3_FIELD_NAME	 			= "Adresse3";
	public final static String CODE_POSTAL_FIELD_NAME	 		= "Codepostal";
	public final static String VILLE_FIELD_NAME	 				= "Ville";
	public final static String TELEPHONE_FIXE_FIELD_NAME	 	= "TelephoneFixe";
	public final static String TELEPHONE_PORTABLE_FIELD_NAME	= "TelephonePortable";
	public final static String EMAIL_FIELD_NAME	 				= "Email";
	public final static String DATE_NAISSANCE_FIELD_NAME	 	= "DateNaissance";
	public final static String CODE_REGION_FIELD_NAME	 		= "CodeRegion";
	public final static String CODE_NATIONALITE_FIELD_NAME	 		= "CodeNationalite";
	public final static String CODE_ORIGINE_MEDIA_FIELD_NAME	= "CodeOrigineMedia";
	public final static String DATE_DERNIER_ENVOI_DOC_FIELD_NAME= "DateDernierEnvoiDoc";
	public final static String DATE_CREATION_FIELD_NAME	 		= "DateCreation";
	public final static String REPERTOIRE_FIELD_NAME	 		= "Repertoire";
	public final static String PERMIS_FIELD_NAME	 			= "Permis";
	public final static String PHOTO_FIELD_NAME	 				= "Photo";
	public final static String ENVOI_DOC_EN_COURS_FIELD_NAME	= "EnvoiDocEnCours";
	public final static String HISTORIQUE_FIELD_NAME			= "Historique";
	
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
		useGetSet = true,
		canBeNull = false)
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
		dataType = DataType.DATE_TIME,
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
		columnName = CODE_ORIGINE_MEDIA_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeOrigineMedia = null;
	
	@DatabaseField(
		columnName = DATE_DERNIER_ENVOI_DOC_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true)
	private DateTime dateDernierEnvoiDoc = null;
	
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true)
	private DateTime dateCreation = null;

	@DatabaseField(
		columnName = REPERTOIRE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String repertoire = null;

	@DatabaseField(
		columnName = PERMIS_FIELD_NAME,
		dataType = DataType.BOOLEAN,
		useGetSet = true,
		canBeNull = false)
	private Boolean permis = null;
	
	@DatabaseField(
		columnName = PHOTO_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String photo = null;

	@DatabaseField(
		columnName = ENVOI_DOC_EN_COURS_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean envoiDocEnCours = null;

	@DatabaseField(
		columnName = HISTORIQUE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String historique = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}
