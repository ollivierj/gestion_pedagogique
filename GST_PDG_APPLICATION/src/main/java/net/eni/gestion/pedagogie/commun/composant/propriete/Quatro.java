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
public class Quatro<E, F, G, H> extends Trio<E, F, G> {
	
	/** Default Serial ID */
	private static final long serialVersionUID = 1L;
	
	private H fourth = null;
	
	public H fourth() {
		return fourth;
	}
	public Quatro() {
	}
	
	public Quatro(E pFirst, F pSecond, G pThird, H pFourth) {
		super(pFirst, pSecond, pThird);
		fourth = pFourth;
	}

	@Override
	public boolean equals(Object pObject) {
		try {
			@SuppressWarnings("unchecked")
			Quatro<E, F, G, H> lQuatro = (Quatro<E, F, G, H>)pObject;
			return 	super.equals(pObject)
				&&	((null != fourth) ? fourth.equals(lQuatro.fourth) : (null == lQuatro.fourth));
		}
		catch(Exception pException) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return 	((null != first) ? (first.hashCode() << 30) : 0)
				+	((null != second) ? (second.hashCode() << 20) : 0)
				+	((null != third) ? (second.hashCode() << 10) : 0)
				+	((null != fourth) ? (third.hashCode()) : 0);
	}
}
