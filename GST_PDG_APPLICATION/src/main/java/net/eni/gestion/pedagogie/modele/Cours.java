/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
@DatabaseTable(tableName = ModeleMetier.COURS_TABLE_NAME)
@XmlRootElement
public class Cours extends AModele<UUID> implements Serializable {
	
	public Cours() {
		super();
	}

	public Cours(UUID pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "IdCours";
	public final static String DEBUT_FIELD_NAME 					= "Debut";
	public final static String FIN_FIELD_NAME						= "Fin";
	public final static String DUREE_REELLE_EN_HEURES_FIELD_NAME	= "DureeReelleEnHeures";
	public final static String CODE_PROMOTION_FIELD_NAME 			= "CodePromotion";
	public final static String PRIX_PUBLIC_AFFECTE_FIELD_NAME 		= "PrixPublicAffecte";
	public final static String DATE_CREATION_FIELD_NAME 			= "DateCreation";
	public final static String DATE_MODIF_FIELD_NAME 				= "DateModif";
	public final static String ID_MODULE_FIELD_NAME 				= "IdModule";
	public final static String LIBELLE_COURS_FIELD_NAME 			= "LibelleCours";
	public final static String DUREE_PREVUE_EN_HEURES_FIELD_NAME 	= "DureePrevueEnHeures";
	public final static String CODE_SALLE_FIELD_NAME 				= "CodeSalle";
	public final static String CODE_FORMATEUR_FIELD_NAME 			= "CodeFormateur";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.UUID,
		id = true,
		generatedId = false,
		useGetSet = true)
	private UUID id = null;
	
	@DatabaseField(
		columnName = DEBUT_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime debut = null;

	@DatabaseField(
		columnName = FIN_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime fin = null;

	@DatabaseField(
		columnName = DUREE_REELLE_EN_HEURES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeReelleEnHeures = null;

	@DatabaseField(
		columnName = CODE_PROMOTION_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Promotion promotion = null;
	
	@DatabaseField(
		columnName = PRIX_PUBLIC_AFFECTE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPublicAffecte = null;

	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateCreation = null;

	@DatabaseField(
		columnName = DATE_MODIF_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateModif = null;

	@DatabaseField(
		columnName = ID_MODULE_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer idModule = null;

	@DatabaseField(
		columnName = LIBELLE_COURS_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelleCours = null;

	@DatabaseField(
		columnName = DUREE_PREVUE_EN_HEURES_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer dureePrevueEnHeures = null;

/*
 * Ces deux champs sont présents en base mais ne sont pas exploités
 */
/*	@DatabaseField(
			columnName = CODE_SALLE_FIELD_NAME,
			foreign = true,
			useGetSet = true)
	private String codeSalle = null;

	@DatabaseField(
			columnName = CODE_FORMATEUR_FIELD_NAME,
			dataType = DataType.INTEGER_OBJ,
			useGetSet = true)
	private Integer codeFormateur = null;*/
	
	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(UUID pId) {
		id = pId;
	}


}
