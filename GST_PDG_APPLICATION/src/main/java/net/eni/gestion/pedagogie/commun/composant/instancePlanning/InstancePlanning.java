package net.eni.gestion.pedagogie.commun.composant.instancePlanning;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;

/**
 * 
 * @author jollivier
 * Manipulation des instances du planning
 * @param <T>
 * @param <U>
 */
@XmlRootElement
public class InstancePlanning<T,U> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Map<T, List<U>> instances;
	List<Pair<T, List<U>>> instances;
	
	List<U> instancesStagiaires;
	
	
	
	public InstancePlanning() {
		super();
	}

	public InstancePlanning(List<Pair<T, List<U>>> instances,
			List<U> instancesStagiaires) {
		this.instances = instances;
		this.instancesStagiaires = instancesStagiaires;
	}

	public List<Pair<T, List<U>>> getInstances() {
		return instances;
	}

	public void setInstances(List<Pair<T, List<U>>> instances) {
		this.instances = instances;
	}

	public List<U> getInstancesStagiaires() {
		return instancesStagiaires;
	}

	public void setInstancesStagiaires(List<U> instancesStagiaires) {
		this.instancesStagiaires = instancesStagiaires;
	}
	
	
}
