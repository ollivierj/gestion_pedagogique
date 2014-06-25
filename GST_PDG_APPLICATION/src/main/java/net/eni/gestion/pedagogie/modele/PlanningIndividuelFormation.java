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
@DatabaseTable(tableName = ModeleMetier.PLANNING_INDIVIDUEL_FORMATION_TABLE_NAME)
@XmlRootElement
public class PlanningIndividuelFormation extends AModele<Integer> implements Serializable {
	
	public PlanningIndividuelFormation() {
		super();
	}

	public PlanningIndividuelFormation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "CodePlanning";
	public final static String CODE_STAGIAIRE_FIELD_NAME 		= "CodeStagiaire";
	public final static String DATE_INSCRIPTION_FIELD_NAME		= "DateInscription";
	public final static String DATE_CREATION_FIELD_NAME 		= "DateCreation";
	public final static String CODE_TYPE_PROFIL_FIELD_NAME	 	= "CodeTypeProfil";
	public final static String CODE_FORMATION_FIELD_NAME 		= "CodeFormation";
	public final static String CODE_PROMOTION_FIELD_NAME 		= "CodePromotion";
	public final static String DATE_MODIF_FIELD_NAME 			= "DateModif";
	public final static String NUM_LIEN_FIELD_NAME	 			= "NumLien";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = CODE_STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;

	@DatabaseField(
		columnName = DATE_INSCRIPTION_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true)
	private DateTime dateInscription = null;

	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true)
	private DateTime dateCreation = null;

	@DatabaseField(
		columnName = CODE_TYPE_PROFIL_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer codeTypeProfil = null;

	@DatabaseField(
		columnName = CODE_FORMATION_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Formation formation = null;
	
	@DatabaseField(
		columnName = CODE_PROMOTION_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Promotion prmotion = null;

	@DatabaseField(
		columnName = DATE_MODIF_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateModif = null;

	@DatabaseField(
		columnName = NUM_LIEN_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true)
	private Integer numLien = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}
