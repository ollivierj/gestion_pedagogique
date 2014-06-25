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
@DatabaseTable(tableName = ModeleMetier.PROMOTION_TABLE_NAME)
@XmlRootElement
public class Promotion extends AModele<String> implements Serializable {
	
	public Promotion() {
		super();
	}

	public Promotion(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "CodePromotion";
	public final static String LIBELLE_FIELD_NAME				= "Libelle";
	public final static String DEBUT_FIELD_NAME					= "Debut";
	public final static String FIN_FIELD_NAME					= "Fin";
	public final static String CODE_FORMATION_FIELD_NAME		= "CodeFormation";
	public final static String PRIX_PUBLIC_AFFECTE_FIELD_NAME	= "PrixPublicAffecte";
	public final static String DATE_MODIF_FIELD_NAME			= "DateModif";
	public final static String DATE_CREATION_FIELD_NAME			= "DateCreation";
	public final static String PRIX_PEC_AFFECTE_FIELD_NAME		= "PrixPECAffecte";
	public final static String PRIX_FINANCE_AFFECTE_FIELD_NAME	= "PrixFinanceAffecte";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.STRING,
		id = true,
		generatedId = false,
		useGetSet = true)
	private String id = null;
	
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;

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
		columnName = CODE_FORMATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Formation formation = null;

	@DatabaseField(
		columnName = PRIX_PUBLIC_AFFECTE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPublicAffecte = null;

	@DatabaseField(
		columnName = DATE_MODIF_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateModif = null;
	
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateCreation = null;

	@DatabaseField(
		columnName = PRIX_PEC_AFFECTE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPecAffecte = null;
	
	@DatabaseField(
		columnName = PRIX_FINANCE_AFFECTE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixFinanceAffecte = null;
		
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String pId) {
		id = pId;
	}


}
