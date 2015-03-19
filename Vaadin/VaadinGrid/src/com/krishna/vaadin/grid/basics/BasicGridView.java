/**
 * 
 */
package com.krishna.vaadin.grid.basics;

import java.text.DateFormat;
import java.util.Set;

import com.krishna.vaadin.grid.MyView;
import com.krishna.vaadin.grid.data.Customer;
import com.krishna.vaadin.grid.data.DatasourceFactory;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;

/**
 * 
 * @author Krishna
 *
 */
public class BasicGridView extends VerticalLayout implements MyView {

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = -4621381457091406226L;

	protected Grid grid;

	private Label label;

	/**
	 * Basic Constructor
	 */
	public BasicGridView() {
		init();
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
		setSpacing(true);
		setImmediate(true);
	}

	/**
	 * Init
	 */
	private void init() {
		// Init the grid with Caption, alternatively can initialize the same
		// with passing the datasource
		grid = new Grid("My Basic Grid");
		grid.setContainerDataSource(getDatasource());
		grid.setImmediate(true);
		initSelectionMode();
		initGridProperties();
		// Init the columns
		initGridColumns();
		initLabel();
		initSelectionListeners();
	}

	/**
	 * Init selection mode
	 */
	private void initSelectionMode() {
		final CheckBox checkBox = new CheckBox("Multi Select");
		addComponent(checkBox);
		checkBox.setImmediate(true);
		checkBox.setValue(false);
		checkBox.addValueChangeListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1261311232228188664L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (checkBox.getValue()) {
					grid.setSelectionMode(SelectionMode.MULTI);
					grid.recalculateColumnWidths();
					// Seems to be some bug in Vaadin Grid when expand ration is
					// not given the column shrinks and this is visible when
					// selection mode is single
					for (Column column : grid.getColumns()) {
						column.setExpandRatio(1);
					}
				} else {
					grid.setSelectionMode(SelectionMode.SINGLE);
				}
			}
		});
	}

	/**
	 * Init Grid Selection Listeners
	 */
	private void initSelectionListeners() {
		grid.addSelectionListener(new SelectionListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7630487081608214268L;

			@Override
			public void select(SelectionEvent event) {
				Set<Object> itemIDs = event.getSelected();
				if (itemIDs.isEmpty()) {
					label.setValue("");
				} else {
					StringBuilder renderingText = new StringBuilder();
					for (Object itemID : itemIDs) {
						Customer customer = (Customer) itemID;
						renderingText.append("Customer with ID - "
								+ customer.getCustomerID() + " and Name - "
								+ customer.getCustomerName() + "<br/>");
					}
					label.setValue(renderingText.toString());
				}

			}
		});
	}

	/**
	 * Init labels
	 */
	private void initLabel() {
		label = new Label();
		label.setImmediate(true);
		label.setContentMode(ContentMode.HTML);
	}

	/**
	 * Initializes basic properties of the grid
	 */
	private void initGridProperties() {
		grid.setHeightMode(HeightMode.ROW);
		grid.setWidth(100, Unit.PERCENTAGE);
		grid.setColumnOrder(new Object[] { "customerName", "city", "pincode",
				"gender" });
		// Hiding is not supported by Grid, in order to remove/hide a column use
		// this API
		grid.removeColumn("customerID");
		Column dob = grid.getColumn("dob");
		dob.setRenderer(new DateRenderer(DateFormat
				.getDateInstance(DateFormat.MEDIUM)));
	}

	private void initGridColumns() {

		Column customerName = grid.getColumn("customerName");
		Column city = grid.getColumn("city");
		Column pincode = grid.getColumn("pincode");
		Column gender = grid.getColumn("gender");
		Column dob = grid.getColumn("dob");
		customerName.setHeaderCaption("Customer Name");
		city.setHeaderCaption("City");
		pincode.setHeaderCaption("Pin Code");
		gender.setHeaderCaption("Gender");
		dob.setHeaderCaption("Date of Birth");

	}

	/**
	 * Sets up datasource that needs to be attached the table
	 * 
	 * @return
	 */
	private Indexed getDatasource() {

		BeanItemContainer<Customer> container = new BeanItemContainer<Customer>(
				Customer.class);
		container.addAll(DatasourceFactory.getCustomerDatasource(20));
		return container;

	}

	@Override
	public void attach() {
		super.attach();
		addComponent(grid);
		addComponent(label);
		setExpandRatio(grid, 1f);
		setExpandRatio(label, 0.5f);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public boolean isCached() {
		return true;
	}

}
