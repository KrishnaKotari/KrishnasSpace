/**
 * 
 */
package com.krishna.vaadin.grid.basics;

import com.krishna.vaadin.grid.data.VehicleInfo;
import com.vaadin.data.Item;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.ui.renderers.ProgressBarRenderer;

/**
 * 
 * This particular class show cases the feature of Generated Columns on grid
 * 
 * @author Krishna
 *
 */
public class GeneratedColumns extends FooterGrid {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2256189435272101574L;

	/**
	 * 
	 */
	public GeneratedColumns() {
		super();
		addGeneratedColumn();
	}

	/**
	 * 
	 */
	private void addGeneratedColumn() {

		GeneratedPropertyContainer container = new GeneratedPropertyContainer(
				grid.getContainerDataSource());
		grid.setContainerDataSource(container);
		// setDefaultGridProperties();
		container.addGeneratedProperty("totalSalesTillDate",
				new PropertyValueGenerator<Double>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = -7625273505835964597L;

					@Override
					public Double getValue(Item item, Object itemId,
							Object propertyId) {
						return getSalesPercentage((VehicleInfo) itemId);
					}

					@Override
					public Class<Double> getType() {
						return Double.class;
					}
				});
		grid.getColumn("totalSalesTillDate").setRenderer(
				new ProgressBarRenderer());
		grid.getColumn("totalSalesTillDate")
				.setHeaderCaption("Sales Till Date");
		// Some how another Id is getting created for display
		container.removeContainerProperty("id");
		container.removeContainerProperty("sales2012.totalSales");
		container.removeContainerProperty("sales2013.totalSales");
		container.removeContainerProperty("sales2014.totalSales");
	}

	/**
	 * Calculates and returns the sales percentage till date. Here 1000000 is
	 * the value of no of sales that a Model can reach per Quarter. Check
	 * Datasource Factory
	 * 
	 * @param vehicleInfo
	 * @return
	 */
	private Double getSalesPercentage(VehicleInfo vehicleInfo) {

		Long y2012 = vehicleInfo.getSales2012().getTotalSales();
		Long y2013 = vehicleInfo.getSales2013().getTotalSales();
		Long y2014 = vehicleInfo.getSales2014().getTotalSales();

		return new Double(y2012 + y2013 + y2014) / (1000000 * 12);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.AbstractGridView#getGridHeader()
	 */
	@Override
	protected String getGridHeader() {
		return "Grid with Generated Columns";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.AbstractGridView#getBriefDesc()
	 */
	@Override
	protected String getBriefDesc() {
		// TODO Auto-generated method stub
		return "Grid to showcase Generated Columns on Grid";
	}

}
