/**
 * 
 */
package com.krishna.vaadin.grid.basics;

import com.krishna.vaadin.grid.data.VehicleInfo;
import com.krishna.vaadin.grid.data.VehicleInfo.VehicleCategory;
import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.TextField;

/**
 * 
 * This class show cases the Filtering of data in grid
 * 
 * @author Krishna
 *
 */
public class FilterGrid extends FooterGrid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7347927626051712831L;

	/**
	 * 
	 */
	public FilterGrid() {
		super();
		setUpFiltersOnGrid();

	}

	/**
	 * 
	 */
	private void setUpFiltersOnGrid() {
		HeaderRow filterRow = grid.appendHeaderRow();
		// Demonstrates combobox in header
		setCategoryFilter(filterRow);
		// Demonstrates TextField
		setManufacturerFilter(filterRow);

	}

	/**
	 * @param filterRow
	 */
	private void setManufacturerFilter(HeaderRow filterRow) {
		HeaderCell categoryFilter = filterRow.getCell(MANUFACTURER);
		TextField textField = new TextField();
		textField.setImmediate(true);
		textField.addTextChangeListener(getManufacturingFilterListener());
		categoryFilter.setComponent(textField);
	}

	/**
	 * @param filterRow
	 * @return
	 */
	private HeaderCell setCategoryFilter(HeaderRow filterRow) {
		HeaderCell categoryFilter = filterRow.getCell(CATEGORY);
		ComboBox comboBox = new ComboBox();
		comboBox.setHeight(100, Unit.PERCENTAGE);
		comboBox.setImmediate(true);
		comboBox.setNewItemsAllowed(false);
		comboBox.setTextInputAllowed(false);
		comboBox.addValueChangeListener(getCategoryFilterListener());
		comboBox.setContainerDataSource(getCategoryDataSource());
		categoryFilter.setComponent(comboBox);
		return categoryFilter;
	}

	/**
	 * @return
	 */
	private Container getCategoryDataSource() {

		IndexedContainer container = new IndexedContainer();
		container.addItem(VehicleCategory.TwoWheeler.toString());
		container.addItem(VehicleCategory.FourWheeler.toString());
		return container;
	}

	/**
	 * @return
	 */
	private TextChangeListener getManufacturingFilterListener() {
		return new TextChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -2368474286053602744L;

			@Override
			public void textChange(TextChangeEvent event) {
				String newValue = (String) event.getText();
				@SuppressWarnings("unchecked")
				BeanItemContainer<VehicleInfo> container = ((BeanItemContainer<VehicleInfo>) grid
						.getContainerDataSource());
				container.removeContainerFilters(MANUFACTURER);
				if (null != newValue && !newValue.isEmpty()) {
					container.addContainerFilter(new SimpleStringFilter(
							MANUFACTURER, newValue, true, false));
				}
				grid.recalculateColumnWidths();
			}
		};
	}

	/**
	 * @return
	 */
	private ValueChangeListener getCategoryFilterListener() {
		return new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -2368474286053602744L;

			@SuppressWarnings("unchecked")
			@Override
			public void valueChange(ValueChangeEvent event) {
				String newValue = (String) event.getProperty().getValue();
				BeanItemContainer<VehicleInfo> container = ((BeanItemContainer<VehicleInfo>) grid
						.getContainerDataSource());
				container.removeContainerFilters(CATEGORY);
				if (null != newValue && !newValue.isEmpty()) {
					container.addContainerFilter(new SimpleStringFilter(
							CATEGORY, newValue, true, true));
				}
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.AbstractGridView#getGridHeader()
	 */
	@Override
	protected String getGridHeader() {
		return "Filter Grid";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.AbstractGridView#getBriefDesc()
	 */
	@Override
	protected String getBriefDesc() {
		// TODO Auto-generated method stub
		return "Grid to showcase Filter Conditions";
	}

}
