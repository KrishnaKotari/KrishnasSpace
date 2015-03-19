package com.krishna.vaadin.grid.data;

import java.io.Serializable;

/**
 * 
 * @author Krishna
 *
 */
public class VehicleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5412465534652505031L;

	/**
	 * Various Vehicle Manufactures in India
	 * 
	 * @author Krishna
	 *
	 */
	public enum Manufacturer {
		Maruti, Honda, Hero, Hyundai, TataMotors, Bajaj
	}

	public enum VehicleCategory {
		TwoWheeler, FourWheeler;
	}

	private Integer id;

	private String modelName;

	private Manufacturer manufacturer;

	private YearlySales sales2013;

	private YearlySales sales2014;

	private YearlySales sales2012;

	private VehicleCategory category;

	/**
	 * @param id
	 * @param modelName
	 * @param manufacturer
	 * @param sales2013
	 * @param sales2014
	 * @param sales2012
	 * @param category
	 */
	public VehicleInfo(Integer id, String modelName, Manufacturer manufacturer,
			YearlySales sales2013, YearlySales sales2014,
			YearlySales sales2012, VehicleCategory category) {
		super();
		this.id = id;
		this.modelName = modelName;
		this.manufacturer = manufacturer;
		this.sales2013 = sales2013;
		this.sales2014 = sales2014;
		this.sales2012 = sales2012;
		this.category = category;
	}

	/**
	 * 
	 */
	public VehicleInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName
	 *            the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the manufacturer
	 */
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the sales2013
	 */
	public YearlySales getSales2013() {
		return sales2013;
	}

	/**
	 * @param sales2013
	 *            the sales2013 to set
	 */
	public void setSales2013(YearlySales sales2013) {
		this.sales2013 = sales2013;
	}

	/**
	 * @return the sales2014
	 */
	public YearlySales getSales2014() {
		return sales2014;
	}

	/**
	 * @param sales2014
	 *            the sales2014 to set
	 */
	public void setSales2014(YearlySales sales2014) {
		this.sales2014 = sales2014;
	}

	/**
	 * @return the sales2015
	 */
	public YearlySales getSales2012() {
		return sales2012;
	}

	/**
	 * @param sales2015
	 *            the sales2015 to set
	 */
	public void setSales2015(YearlySales sales2012) {
		this.sales2012 = sales2012;
	}

	/**
	 * @return the category
	 */
	public VehicleCategory getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(VehicleCategory category) {
		this.category = category;
	}

	/**
	 * @param sales2012
	 *            the sales2012 to set
	 */
	public void setSales2012(YearlySales sales2012) {
		this.sales2012 = sales2012;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VehicleInfo)) {
			return false;
		}
		VehicleInfo other = (VehicleInfo) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
