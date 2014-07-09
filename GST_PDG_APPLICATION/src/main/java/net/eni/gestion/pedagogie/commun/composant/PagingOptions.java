package net.eni.gestion.pedagogie.commun.composant;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PagingOptions {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int[] pageSizes;
	private int pageSize;
	private int currentPage;
	
	/**
	 * Constructeur à vide nécéssaire pour la sérialisation
	 */
	public PagingOptions() {
		super();
	}

	public int[] getPageSizes() {
		return pageSizes;
	}

	public void setPageSizes(int[] pageSizes) {
		this.pageSizes = pageSizes;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	
}
