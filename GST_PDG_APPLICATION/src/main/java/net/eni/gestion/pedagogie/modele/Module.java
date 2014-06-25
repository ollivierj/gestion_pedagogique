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
@DatabaseTable(tableName = ModeleMetier.MODULE_TABLE_NAME)
@XmlRootElement
public class Module extends AModele<Integer> implements Serializable {
	
	public Module() {
		super();
	}

	public Module(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "IdModule";
	public final static String LIBELLE_FIELD_NAME 				= "Libelle";
	public final static String DUREE_EN_HEURES_FIELD_NAME		= "DureeEnHeures";
	public final static String DATE_CREATION_FIELD_NAME 		= "DateCreation";
	public final static String DUREE_EN_SEMAINES_FIELD_NAME 	= "DureeEnSemaines";
	public final static String PRIX_PUBLIC_EN_COURS_FIELD_NAME 	= "PrixPublicEnCours";
	public final static String LIBELLE_COURT_FIELD_NAME 		= "LibelleCourt";
	public final static String DATE_MODIF_FIELD_NAME 			= "DateModif";
	public final static String ARCHIVER_FIELD_NAME 				= "Archiver";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;

	@DatabaseField(
		columnName = DUREE_EN_HEURES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeEnHeures = null;

	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateCreation = null;
		
	@DatabaseField(
		columnName = DUREE_EN_SEMAINES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeEnSemaines = null;
	
	@DatabaseField(
		columnName = PRIX_PUBLIC_EN_COURS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPublicEnCours = null;
	
	@DatabaseField(
		columnName = LIBELLE_COURT_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelleCourt = null;
	
	@DatabaseField(
		columnName = DUREE_EN_SEMAINES_FIELD_NAME,
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
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}
