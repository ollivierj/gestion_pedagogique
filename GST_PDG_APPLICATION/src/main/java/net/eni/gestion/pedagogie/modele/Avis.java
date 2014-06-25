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
@DatabaseTable(tableName = ModeleMetier.AVIS_TABLE_NAME)
@XmlRootElement
public class Avis extends AModele<Integer> implements Serializable {
	
	public Avis() {
		super();
	}

	public Avis(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "AVIS_ID";
	public final static String STAGIAIRE_FIELD_NAME 				= "AVIS_STAGIAIRE";
	public final static String INSTANCE_COURS_FIELD_NAME			= "AVIS_INSTANCE_COURS";
	public final static String TEXTE_FIELD_NAME						= "AVIS_TEXTE";
	public final static String DATE_SAISIE_FIELD_NAME 				= "AVIS_DATE_SAISIE";
	public final static String AUTEUR_FIELD_NAME 					= "AVIS_AUTEUR";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;

	@DatabaseField(
		columnName = INSTANCE_COURS_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private InstanceCours instanceCours = null;

	@DatabaseField(
		columnName = TEXTE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private DateTime texte = null;

	@DatabaseField(
		columnName = DATE_SAISIE_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateSaisie = null;
	
	@DatabaseField(
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Utilisateur auteur = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}