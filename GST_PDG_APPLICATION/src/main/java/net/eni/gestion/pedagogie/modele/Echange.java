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
@DatabaseTable(tableName = ModeleMetier.ECHANGE_TABLE_NAME)
@XmlRootElement
public class Echange extends AModele<Integer> implements Serializable {
	
	public Echange() {
		super();
	}

	public Echange(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 			= "ECH_ID";
	public final static String AUTEUR_FIELD_NAME 		= "ECH_AUTEUR";
	public final static String STAGIAIRE_FIELD_NAME 	= "ECH_STAGIAIRE";
	public final static String COMMENTAIRE_FIELD_NAME 	= "ECH_COMMENTAIRE";
	public final static String DATE_SAISIE_FIELD_NAME 	= "ECH_DATE_SAISIE";

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
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;

	@DatabaseField(
		columnName = COMMENTAIRE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String commentaire = null;
	
	@DatabaseField(
		columnName = DATE_SAISIE_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime versionEchange = null;
		
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public DateTime getVersionEchange() {
		return versionEchange;
	}

	public void setVersionEchange(DateTime versionEchange) {
		this.versionEchange = versionEchange;
	}

}
