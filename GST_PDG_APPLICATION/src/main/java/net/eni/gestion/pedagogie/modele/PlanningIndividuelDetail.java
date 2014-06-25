/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
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
@DatabaseTable(tableName = ModeleMetier.PLANNING_INDIVIDUEL_DETAIL_TABLE_NAME)
@XmlRootElement
public class PlanningIndividuelDetail extends AModele<String> implements Serializable {
	
	public PlanningIndividuelDetail() {
		super();
	}

	public PlanningIndividuelDetail(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID1_FIELD_NAME 						= "CodePlanning";
	public final static String ID2_FIELD_NAME 						= "IdCours";
	public final static String PRIX_COURS_DEVIS_FIELD_NAME			= "PrixCoursDevis";
	public final static String PRIX_COURS_PEC_DEVIS_FIELD_NAME 		= "PrixCoursPECDevis";
	public final static String PRIX_COURS_FINANCE_DEVIS_FIELD_NAME 	= "PrixCoursPECDevis";
	public final static String DISPENSE_FIELD_NAME 					= "Dispense";
	public final static String INSCRIT_FIELD_NAME 					= "Inscrit";
	public final static String DEBUT_COURS_FIELD_NAME 				= "DebutCours";
	public final static String FIN_COURS_FIELD_NAME 				= "FinCours";
	public final static String HEURES_REELLES_COURS_FIELD_NAME 		= "HeuresReellesCours";

	@DatabaseField(
		columnName = ID1_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id1 = null;
	
	@DatabaseField(
		columnName = ID2_FIELD_NAME,
		dataType = DataType.UUID,
		id = true,
		generatedId = false,
		useGetSet = true)
	private UUID id2 = null;

	@DatabaseField(
		columnName = PRIX_COURS_DEVIS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixCoursDevis = null;

	@DatabaseField(
		columnName = PRIX_COURS_PEC_DEVIS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true)
	private Float prixCoursPECDevis = null;

	@DatabaseField(
		columnName = PRIX_COURS_FINANCE_DEVIS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true)
	private Float prixCoursFinanceDevis = null;

	@DatabaseField(
		columnName = DISPENSE_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean dispense = null;
	
	@DatabaseField(
		columnName = INSCRIT_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean Inscrit = null;

	@DatabaseField(
		columnName = DEBUT_COURS_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true)
	private DateTime debutCours = null;

	@DatabaseField(
		columnName = FIN_COURS_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true)
	private DateTime finCours = null;

	@DatabaseField(
		columnName = HEURES_REELLES_COURS_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true)
	private Short heuresRellesCours = null;
		
	@Override
	public String getId() {
		return id2 + "/" + id2;
	}

	@Override
	public void setId(String pId) {
		String[] ids = pId.split("/");;
		setId1(Integer.valueOf(ids[0]));
		setId2(UUID.fromString(ids[1]));
	}

	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public UUID getId2() {
		return id2;
	}

	public void setId2(UUID id2) {
		this.id2 = id2;
	} 


}
