/**
 * 
 */
package com.krishna.vaadin.grid.data;

import java.io.Serializable;

/**
 * @author Krishna
 *
 */
public class YearlySales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -893627659352850558L;

	private Integer q1;

	private Integer q2;

	private Integer q3;

	private Integer q4;

	/**
	 * 
	 */
	public YearlySales() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param q1
	 * @param q2
	 * @param q3
	 * @param q4
	 */
	public YearlySales(Integer q1, Integer q2, Integer q3, Integer q4) {
		super();
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
		this.q4 = q4;
	}

	/**
	 * @return the q1
	 */
	public Integer getQ1() {
		return q1;
	}

	/**
	 * @param q1
	 *            the q1 to set
	 */
	public void setQ1(Integer q1) {
		this.q1 = q1;
	}

	/**
	 * @return the q2
	 */
	public Integer getQ2() {
		return q2;
	}

	/**
	 * @param q2
	 *            the q2 to set
	 */
	public void setQ2(Integer q2) {
		this.q2 = q2;
	}

	/**
	 * @return the q3
	 */
	public Integer getQ3() {
		return q3;
	}

	/**
	 * @param q3
	 *            the q3 to set
	 */
	public void setQ3(Integer q3) {
		this.q3 = q3;
	}

	/**
	 * @return the q4
	 */
	public Integer getQ4() {
		return q4;
	}

	/**
	 * @param q4
	 *            the q4 to set
	 */
	public void setQ4(Integer q4) {
		this.q4 = q4;
	}

	/**
	 * Method that calculates and returns total sales
	 * 
	 * @return
	 */
	public Long getTotalSales() {
		return Long.valueOf(q1 + q2 + q3 + q4);
	}
}
