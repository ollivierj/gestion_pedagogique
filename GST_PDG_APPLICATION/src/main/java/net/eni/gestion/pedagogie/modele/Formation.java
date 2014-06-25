/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.Date;

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
@DatabaseTable(tableName = ModeleMetier.FORMATION_TABLE_NAME)
@XmlRootElement
public class Formation extends AModele<String> implements Serializable {
	
	public Formation() {
		super();
	}

	public Formation(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "CodeFormation";
	public final static String LIBELLE_LONG_FIELD_NAME 			= "LibelleLong";
	public final static String LIBELLE_COURT_FIELD_NAME 		= "LibelleCourt";
	public final static String DUREE_EN_HEURES_FIELD_NAME 		= "DureeEnHeures";
	public final static String TAUX_HORAIRE_FIELD_NAME 			= "TauxHoraire";
	public final static String DATE_CREATION_FIELD_NAME 		= "DateCreation";
	public final static String CODE_TITRE_FIELD_NAME 			= "CodeTitre";
	public final static String PRIX_PUBLIC_EN_COURS_FIELD_NAME 	= "PrixPublicEnCours";
	public final static String HEURES_CENTRE_FIELD_NAME 		= "HeuresCentre";
	public final static String HEURES_STAGE_FIELD_NAME 			= "HeuresStage";
	public final static String SEMAINES_CENTRE_FIELD_NAME 		= "SemainesCentre";
	public final static String SEMAINES_STAGE_FIELD_NAME 		= "SemainesStage";
	public final static String DUREE_EN_SEMAINES_FIELD_NAME 	= "DureeEnSemaines";
	public final static String DATE_MODIF_FIELD_NAME 			= "DateModif";
	public final static String ARCHIVER_FIELD_NAME 				= "Archiver";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.STRING,
		id = true,
		generatedId = false,
		useGetSet = true)
	private String id = null;
	
	@DatabaseField(
		columnName = LIBELLE_LONG_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelleLong = null;

	@DatabaseField(
		columnName = LIBELLE_COURT_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelleCourt = null;
	
	@DatabaseField(
		columnName = DUREE_EN_HEURES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeEnHeures = null;

	@DatabaseField(
		columnName = TAUX_HORAIRE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float tauxHoraire = null;
	
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateCreation = null;
	
	@DatabaseField(
		columnName = CODE_TITRE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeTitre = null;
	
	@DatabaseField(
		columnName = PRIX_PUBLIC_EN_COURS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPublicEnCours = null;
	
	@DatabaseField(
		columnName = HEURES_CENTRE_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short heuresCentre = null;

	@DatabaseField(
		columnName = HEURES_STAGE_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short heuresStage = null;
	
	@DatabaseField(
		columnName = SEMAINES_CENTRE_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short semainesCentre = null;

	@DatabaseField(
		columnName = SEMAINES_STAGE_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short semainesStage = null;
	
	@DatabaseField(
		columnName = DUREE_EN_SEMAINES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeEnSemaines = null;

	@DatabaseField(
		columnName = DATE_MODIF_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateModif = null;

	@DatabaseField(
		columnName = ARCHIVER_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean archiver = null;
		
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String pId) {
		id = pId;
	}


}
