package net.eni.gestion.pedagogie.commun.composant;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SortOptions implements Serializable {

	private static final long serialVersionUID = 1L;

	private String[] fields;
	private String[] directions;

	/**
	 * Constructeur à vide nécéssaire pour la sérialisation
	 */
	public SortOptions() {
		super();
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String[] getDirections() {
		return directions;
	}

	public void setDirections(String[] directions) {
		this.directions = directions;
	}

}
