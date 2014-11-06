package net.eni.gestion.pedagogie.commun.composant.tuple;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jollivier
 * Manipulation de tuples typés
 * @param <E>
 * @param <F>
 */
@XmlRootElement
public class Pair<E,F> implements Serializable/*, Cloneable*/ {
	
	/** Default Serial ID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Premier élément du tuple
	 */
	protected E first = null;
	
	
	/**
	 * Second élément du tuple
	 */
	protected F second = null;
	
	/**
	 * Getter pour le premier élément
	 * @return premier élément
	 */
	public E first() {
		return first;
	}

	/**
	 * Getter pour le second élément
	 * @return second élément
	 */
	public F second() {
		return second;
	}

	/**
	 * Constructeur du tuple
	 */
	public Pair() {
	}
	
	/**Constructeur paramétré du tuple
	 * @param premier élément
	 * @param second élément
	 */
	public Pair(E pFirst, F pSecond) {
		first = pFirst;
		second = pSecond;
	}

	@Override
	public boolean equals(Object pObject) {
		try {
			@SuppressWarnings("unchecked")
			Pair<E, F> lPair = (Pair<E, F>)pObject;
			return 	((null != first) ? first.equals(lPair.first) : (null == lPair.second))
				&&	((null != second) ? second.equals(lPair.second) : (null == lPair.second));
		}
		catch(Exception pException) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return 	((null != first) ? (first.hashCode() << 16) : 0)
			+	((null != second) ? (second.hashCode()) : 0);
	}

	public E getFirst() {
		return first;
	}

	public void setFirst(E first) {
		this.first = first;
	}

	public F getSecond() {
		return second;
	}

	public void setSecond(F second) {
		this.second = second;
	}	
	
	
}
