/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlRootElement;
import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.INSTANCE_EVALUATION_TABLE_NAME)
@XmlRootElement
public class InstanceEvaluation extends AModele<Integer> implements Serializable {
	
	public InstanceEvaluation() {
		super();
	}

	public InstanceEvaluation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "INST_ECF_ID";
	public final static String RESERVATION_SALLE_FIELD_NAME 		= "INST_ECF_RESERVATION_SALLE";
	public final static String SURVEILLANT_FIELD_NAME 				= "INST_ECF_SURVELLANT";
	public final static String CORRECTEUR_FIELD_NAME 				= "INST_ECF_CORRECTEUR";
	public final static String EVALUATION_FIELD_NAME 				= "INST_ECF_EVALUATION";
	public final static String LIEN_GRILLE_CORRECTION_FIELD_NAME 	= "INST_ECF_LIEN_GRILLE_CORRECTION";
	public final static String LIEN_COPIES_IMMATERRIELLES_FIELD_NAME= "INST_ECF_LIEN_COPIES_IMMATERRIELLES";
	public final static String DATE_DEBUT_PASSAGE_FIELD_NAME 		= "INST_ECF_DATE_DEBUT_PASSAGE";
	public final static String DATE_FIN_PASSAGE_FIELD_NAME 			= "INST_ECF_DATE_FIN_PASSAGE";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = RESERVATION_SALLE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private ReservationSalle reservationSalle = null;

	@DatabaseField(
		columnName = SURVEILLANT_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur surveillant = null;

	@DatabaseField(
		columnName = CORRECTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur correcteur = null;
	
	@DatabaseField(
		columnName = EVALUATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Evaluation evaluation = null;
	
	@DatabaseField(
		columnName = LIEN_GRILLE_CORRECTION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String lienGrilleCorrection = null;
	
	@DatabaseField(
		columnName = LIEN_COPIES_IMMATERRIELLES_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String lienCopiesImmaterrielles = null;
	
	@DatabaseField(
		columnName = DATE_DEBUT_PASSAGE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateDebutPassage = null;

	@DatabaseField(
		columnName = DATE_FIN_PASSAGE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateFinPassage = null;
	
	@ForeignCollectionField(eager = true, columnName = InstanceEvaluationStagiaire.ID2_FIELD_NAME)
	private transient Collection<InstanceEvaluationStagiaire> transientInstanceEvaluationStagiaires = null;

	private ArrayList<InstanceEvaluationStagiaire> instanceEvaluationStagiaires = new ArrayList<InstanceEvaluationStagiaire>();

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public ReservationSalle getReservationSalle() {
		return reservationSalle;
	}

	public void setReservationSalle(ReservationSalle reservationSalle) {
		this.reservationSalle = reservationSalle;
	}

	public Utilisateur getSurveillant() {
		return surveillant;
	}

	public void setSurveillant(Utilisateur surveillant) {
		this.surveillant = surveillant;
	}

	public Utilisateur getCorrecteur() {
		return correcteur;
	}

	public void setCorrecteur(Utilisateur correcteur) {
		this.correcteur = correcteur;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public String getLienGrilleCorrection() {
		return lienGrilleCorrection;
	}

	public void setLienGrilleCorrection(String lienGrilleCorrection) {
		this.lienGrilleCorrection = lienGrilleCorrection;
	}

	public String getLienCopiesImmaterrielles() {
		return lienCopiesImmaterrielles;
	}

	public void setLienCopiesImmaterrielles(String lienCopiesImmaterrielles) {
		this.lienCopiesImmaterrielles = lienCopiesImmaterrielles;
	}

	public Date getDateDebutPassage() {
		return dateDebutPassage;
	}

	public void setDateDebutPassage(Date dateDebutPassage) {
		this.dateDebutPassage = dateDebutPassage;
	}

	public Date getDateFinPassage() {
		return dateFinPassage;
	}

	public void setDateFinPassage(Date dateFinPassage) {
		this.dateFinPassage = dateFinPassage;
	}

	private ArrayList<InstanceEvaluationStagiaire> getInstanceEvaluationStagiaires() {
		if (null != transientInstanceEvaluationStagiaires) {
			instanceEvaluationStagiaires.clear();
			instanceEvaluationStagiaires.addAll(transientInstanceEvaluationStagiaires);
			transientInstanceEvaluationStagiaires = null;
		}
		return instanceEvaluationStagiaires;
	}
	
	public ArrayList<Stagiaire> getStagiaires(){
		ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
		if (0 < getInstanceEvaluationStagiaires().size()) {
			Iterator<InstanceEvaluationStagiaire> iterator = getInstanceEvaluationStagiaires().iterator();
			while (iterator.hasNext()) {
				stagiaires.add(iterator.next().getStagiaire());
			}
		}
		return stagiaires;
	}
	
}
