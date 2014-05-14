/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



/**
 * @author emarquis
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

	public final static String ID_FIELD_NAME 				= "CodeStagiaire";
	public final static String NOM_FIELD_NAME 				= "Nom";
	public final static String PRENOM_FIELD_NAME 			= "Prenom";
	public final static String ADRESSE1_FIELD_NAME 			= "Adresse1";
	public final static String PERMIS_FIELD_NAME 			= "Permis";
	public final static String ENVOIDOCENCOURS_FIELD_NAME 	= "EnvoiDocEnCours";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer CodeStagiaire = null;
	
	@DatabaseField(
			columnName = NOM_FIELD_NAME,
			dataType = DataType.STRING,
			useGetSet = true)
	private String Nom = null;

	@DatabaseField(
			columnName = PRENOM_FIELD_NAME,
			dataType = DataType.STRING,
			useGetSet = true)
	private String Prenom = null;

	@DatabaseField(
			columnName = ADRESSE1_FIELD_NAME,
			dataType = DataType.STRING,
			useGetSet = true,
			defaultValue = "",
			canBeNull = false)
	private String Adresse1 = null;

	@DatabaseField(
			columnName = PERMIS_FIELD_NAME,
			dataType = DataType.BOOLEAN_OBJ,
			useGetSet = true,
			defaultValue = "false",
			canBeNull = false)
	private Boolean Permis = null;

	@DatabaseField(
			columnName = ENVOIDOCENCOURS_FIELD_NAME,
			dataType = DataType.BOOLEAN_OBJ,
			useGetSet = true, 
			defaultValue = "false",
			canBeNull = false)
	private Boolean EnvoiDocEnCours = null;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return CodeStagiaire;
	}

	@Override
	public void setId(Integer pId) {
		CodeStagiaire = pId;
	}
	
	public Integer getCodeStagiaire() {
		return CodeStagiaire;
	}

	public void setCodeStagiaire(Integer codeStagiaire) {
		CodeStagiaire = codeStagiaire;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	
	public String getAdresse1() {
		return Adresse1;
	}

	public void setAdresse1(String adresse) {
		Adresse1 = adresse;
	}

	public Boolean getPermis() {
		return Permis;
	}

	public void setPermis(Boolean permis) {
		Permis = permis;
	}

	public Boolean getEnvoiDocEnCours() {
		return EnvoiDocEnCours;
	}

	public void setEnvoiDocEnCours(Boolean envoiDocEnCours) {
		EnvoiDocEnCours = envoiDocEnCours;
	}

}
