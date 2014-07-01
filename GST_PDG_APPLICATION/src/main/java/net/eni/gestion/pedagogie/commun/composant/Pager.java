/**
 * 
 */
package net.eni.gestion.pedagogie.commun.composant;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jollivie
 * 
 */
@XmlRootElement
public class Pager implements Serializable {

	/**
	 * Constructeur à vide nécéssaire pour la sérialisation
	 */
	public Pager() {
		super();
	}
	
	public Pager(int page, int pageSize, String sortColumnBy,
			String sortDirectionBy) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.sortColumnBy = sortColumnBy;
		this.sortDirectionBy = sortDirectionBy;
	}

	/**
	 * Constructeur paramètré
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * index de debut
	 */
	private int page;

	/**
	 * index de fin
	 */
	private int pageSize;

	/**
	 * colonnes de tri
	 */
	private String sortColumnBy;

	/**
	 * sens de tri
	 */
	private String sortDirectionBy;

	/**
	 * @return
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return
	 */
	public String getSortColumnBy() {
		return sortColumnBy;
	}

	/**
	 * @param sortColumnBy
	 */
	public void setSortColumnBy(String sortColumnBy) {
		this.sortColumnBy = sortColumnBy;
	}

	/**
	 * @return
	 */
	public String getSortDirectionBy() {
		return sortDirectionBy;
	}

	/**
	 * @param sortDirectionBy
	 */
	public void setSortDirectionBy(String sortDirectionBy) {
		this.sortDirectionBy = sortDirectionBy;
	}

	
}
