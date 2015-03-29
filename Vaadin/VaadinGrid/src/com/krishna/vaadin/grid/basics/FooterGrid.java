/**
 * 
 */
package com.krishna.vaadin.grid.basics;

import com.krishna.vaadin.grid.data.VehicleInfo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.StringToLongConverter;
import com.vaadin.ui.Grid.FooterCell;
import com.vaadin.ui.Grid.FooterRow;

/**
 * 
 * Class that show cases Footer
 * 
 * @author Krishna
 *
 */
public class FooterGrid extends HeaderGrid {

	/**
	 * 
	 */
	private static final long serialVersionUID = -675554642434107113L;

	protected static final String CATEGORY = "category";

	protected static final String MANUFACTURER = "manufacturer";

	protected static final String MODEL_NAME = "modelName";

	/**
	 * 
	 */
	public FooterGrid() {
		super();
		setUpFooter();
	}

	/**
	 * Method to setup Footer
	 */
	private void setUpFooter() {

		FooterRow mainFooter = grid.prependFooterRow();
		FooterCell mainFooterCell = mainFooter.join(CATEGORY, MANUFACTURER,
				MODEL_NAME);
		mainFooterCell.setText("Total Vehicles Sold Per Quarter");
		FooterRow secondaryFooter = grid.appendFooterRow();
		FooterCell secondaryFooterCell = secondaryFooter.join(CATEGORY,
				MANUFACTURER, MODEL_NAME);
		secondaryFooterCell.setText("Total Vehicles Sold Per Year");
		setUpFooterForDataConsolidation(mainFooter, secondaryFooter);
	}

	/**
	 * 
	 * Note: Variable names are not followed This is for Calculating
	 * QuarterlyValues, naming convention was not used to make is easy for
	 * tutorial
	 * 
	 * @param mainFooter
	 * @param secondaryFooter
	 */
	private void setUpFooterForDataConsolidation(FooterRow mainFooter,
			FooterRow secondaryFooter) {

		@SuppressWarnings("unchecked")
		BeanItemContainer<VehicleInfo> container = (BeanItemContainer<VehicleInfo>) grid
				.getContainerDataSource();
		// String to Long Converter
		Converter<String, Long> convertor = new StringToLongConverter();
		for (int i = 2012; i < 2015; i++) {
			FooterCell yearlySalesCell = secondaryFooter.join("sales" + i
					+ ".q1", "sales" + i + ".q2", "sales" + i + ".q3", "sales"
					+ i + ".q4");
			long yearlySalesValue = 0l;
			// Iterating over all items and calculating values over each quarter
			for (int quarter = 1; quarter <= 4; quarter++) {
				long quarterSales = 0l;
				final String columnName = "sales" + i + ".q" + quarter;
				FooterCell sumOfQuarterlySales = mainFooter.getCell(columnName);
				// Iterate over each quarter and calculate
				for (Object itemId : container.getItemIds()) {
					Item item = container.getItem(itemId);
					Integer q1Value = (Integer) item
							.getItemProperty(columnName).getValue();
					quarterSales += Long.valueOf(q1Value);
				}
				sumOfQuarterlySales.setText(convertor.convertToPresentation(
						quarterSales, String.class, grid.getLocale()));
				yearlySalesValue += quarterSales;
			}
			yearlySalesCell.setText(convertor.convertToPresentation(
					yearlySalesValue, String.class, grid.getLocale()));
			yearlySalesCell.setHtml("<b>"
					+ convertor.convertToPresentation(yearlySalesValue,
							String.class, grid.getLocale()) + " <b>");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.HeaderGrid#getGridHeader()
	 */
	@Override
	protected String getGridHeader() {

		return "Footer Grid";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.HeaderGrid#getBriefDesc()
	 */
	@Override
	protected String getBriefDesc() {
		return "Class to showcase Footer on Grid";
	}

}
