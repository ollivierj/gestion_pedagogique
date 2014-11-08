/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.COURS_STAGIAIRE_TABLE_NAME)
@XmlRootElement
public class CoursStagiaire extends AModele<Integer> implements Serializable {
	
	public CoursStagiaire() {
		super();
	}

	public CoursStagiaire(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 			= "COURS_STG_ID";
	public final static String COURS_FIELD_NAME 		= "COURS_STG_COURS";
	public final static String STAGIAIRE_FIELD_NAME 	= "COURS_STG_STAGIAIRE";
	public final static String INSTANCE_COURS_FIELD_NAME= "COURS_STG_INSTANCE_COURS";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@JsonBackReference("CoursStagiaire-Cours")
	@DatabaseField(
		columnName = COURS_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Cours cours = null;

	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false,
		foreignAutoRefresh = true)
	private StagiairePromotion stagiaire = null;

	@DatabaseField(
			columnName = INSTANCE_COURS_FIELD_NAME,
			foreign = true,
			useGetSet = true,
			canBeNull = false)
		private InstanceCours instanceCours = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id=pId;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public StagiairePromotion getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(StagiairePromotion stagiaire) {
		this.stagiaire = stagiaire;
	}

	public InstanceCours getInstanceCours() {
		return instanceCours;
	}

	public void setInstanceCours(InstanceCours instanceCours) {
		this.instanceCours = instanceCours;
	}
	

}
