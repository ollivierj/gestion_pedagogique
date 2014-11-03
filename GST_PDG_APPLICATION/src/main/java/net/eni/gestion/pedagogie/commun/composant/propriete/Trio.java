/**
 * 
 */
package net.eni.gestion.pedagogie.commun.composant.propriete;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jollivier
 *
 */
@XmlRootElement
public class Trio<E, F, G> extends Pair<E, F> {
	
	/** Default Serial ID */
	private static final long serialVersionUID = 1L;
	
	protected G third = null;
	
	public G third() {
		return third;
	}
	public Trio() {
	}
	
	public Trio(E pFirst, F pSecond, G pThird) {
		super(pFirst, pSecond);
		third = pThird;
	}

	@Override
	public boolean equals(Object pObject) {
		try {
			@SuppressWarnings("unchecked")
			Trio<E, F, G> lTrio = (Trio<E, F, G>)pObject;
			return 	super.equals(pObject)
				&&	((null != third) ? third.equals(lTrio.third) : (null == lTrio.third));
		}
		catch(Exception pException) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return 	((null != first) ? (first.hashCode() << 20) : 0)
			+	((null != second) ? (second.hashCode() << 10) : 0)
			+	((null != third) ? (third.hashCode()) : 0);
	}
}
