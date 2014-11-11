/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reinert.jjschema.Attributes;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.SALLE_TABLE_NAME)
@XmlRootElement
public class Salle extends AModele<Integer> implements Serializable {
	
	public Salle() {
		super();
	}

	public Salle(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 		= "SALLE_REF_ID";
	public final static String LIBELLE_FIELD_NAME	= "SALLE_REF_LIBELLE";
	public final static String NBPLACE_FIELD_NAME	= "SALLE_REF_NB_PLACES";
	public final static String LIEU_FIELD_NAME		= "SALLE_REF_LIEU";
	

	public final static String[] FULL_TEXT_SEARCH_FIELDS	= {LIBELLE_FIELD_NAME, LIEU_FIELD_NAME};
	
	@JsonIgnore
	@Override
	public String[] getFullTextSearchFieldNames() {
		return FULL_TEXT_SEARCH_FIELDS;
	}
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@Attributes(title = "Libellé", required = true, maxLength = 100)
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;
	
	@Attributes(title = "Nombre de places", required = true)
	@DatabaseField(
		columnName = NBPLACE_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer nbPlaces = null;
	
	@Attributes(title = "Lieu", required = true, maxLength = 250)
	@DatabaseField(
		columnName = LIEU_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lieu = null;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getLieu())?getLieu():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getLibelle())?getLibelle():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getNbPlaces())?getNbPlaces():"");
		lStrStringBuilder.append(";");
		return lStrStringBuilder.toString();
	}

}
