package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@XmlRootElement
@DatabaseTable(tableName = ModeleMetier.PLANNING_VIEW_NAME)
public class Planning extends AModele<Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String ID_FIELD_NAME 			= "Id";
	public final static String ELEMENT_FIELD_NAME 		= "Entity_Id";
	public final static String TYPE_FIELD_NAME 			= "Type";
	public final static String LIBELLE_FIELD_NAME 		= "Libelle";
	public final static String DATE_DEBUT_FIELD_NAME 	= "Debut";
	public final static String DATE_FIN_FIELD_NAME 		= "Fin";
	public final static String SALLES_FIELD_NAME 		= "Salles";
	
	public Planning() {
		super();
	}

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.LONG_OBJ,
		id = true,
		useGetSet = true)
	private Long id = null;
	
	@DatabaseField(
		columnName = ELEMENT_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String elementId = null;
	
	@DatabaseField(
		columnName = TYPE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String type = null;
	
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String libelle = null;
	
	//@JsonIgnore
	@DatabaseField(
		columnName = DATE_DEBUT_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date debut = null;
	
//	@JsonProperty
//	private String start = null;
	
	//@JsonIgnore
	@DatabaseField(
		columnName = DATE_FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date fin = null;
	
//	@JsonProperty
//	private String end = null;
	
	@DatabaseField(
		columnName = SALLES_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String salles = null;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long pId) {
		this.id = pId;
		
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
//		this.debut= DateHelper.stringifyDate(debut, pFormat)(start, "dd/MM/yyyy HH:mm");
		this.debut = debut;
	}
	
//	public String getStart() {
//		return start;
//	}
//
//	public void setStart(String start) {
//		this.debut= DateHelper.datifyString(start, "dd/MM/yyyy HH:mm");
//		this.start = start;
//	}

//	public String getEnd() {
//		return end;
//	}
//
//	public void setEnd(String end) {
//		this.end = end;
//	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public String getSalles() {
		return salles;
	}

	public void setSalles(String salles) {
		this.salles = salles;
	}
	

}
