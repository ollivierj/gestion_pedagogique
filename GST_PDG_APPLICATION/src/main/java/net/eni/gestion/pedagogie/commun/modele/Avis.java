/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;
import net.eni.gestion.pedagogie.commun.outil.DateHelper;

import com.github.reinert.jjschema.Attributes;
import com.github.reinert.jjschema.SchemaIgnore;
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

	public final static String ID_FIELD_NAME 				= "AVIS_ID";
	public final static String STAGIAIRE_FIELD_NAME 		= "AVIS_STAGIAIRE";
	public final static String INSTANCE_COURS_FIELD_NAME	= "AVIS_INSTANCE_COURS";
	public final static String TEXTE_FIELD_NAME				= "AVIS_TEXTE";
	public final static String AUTEUR_FIELD_NAME 			= "AVIS_AUTEUR";
	public final static String DATE_FIELD_NAME 				= "AVIS_DATE";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@SchemaIgnore
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;

	@SchemaIgnore
	@DatabaseField(
		columnName = INSTANCE_COURS_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private InstanceCours instanceCours = null;

	@Attributes(title = "Commentaire", required = true, maxLength = 255)
	@DatabaseField(
		columnName = TEXTE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String texte = null;
	
	@SchemaIgnore
	@DatabaseField(
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Utilisateur auteur = null;
	
	@DatabaseField(
		columnName = DATE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date date = null;
	
	@Attributes(title = "Date", required = true, format = "date")
	private String formatedDate;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public InstanceCours getInstanceCours() {
		return instanceCours;
	}

	public void setInstanceCours(InstanceCours instanceCours) {
		this.instanceCours = instanceCours;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.formatedDate=DateHelper.stringifyDate(date, "yyyy-MM-dd");
		this.date = date;
	}
	
	public String getFormatedDate() {
		return formatedDate;
	}

	public void setFormatedDate(String formatedDate) throws ParseException {
		this.date= DateHelper.datifyString(formatedDate, "yyyy-MM-dd");
		this.formatedDate = formatedDate;
	}
	
	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getFormatedDate())?getFormatedDate():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getStagiaire())?(null!= getStagiaire().getPrenom())?getStagiaire().getPrenom():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getStagiaire())?(null!= getStagiaire().getNom())?getStagiaire().getNom():"":"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getTexte())?getTexte():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getAuteur())?(null!=getAuteur().getPrenom())?getAuteur().getPrenom():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getAuteur())?(null!=getAuteur().getPrenom())?getAuteur().getPrenom():"":"");
		lStrStringBuilder.append(";");
		return lStrStringBuilder.toString();
	}
}
