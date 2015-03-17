/**
 * 
 */
package com.krishna.vaadin.grid.basics;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

import com.krishna.vaadin.grid.MyView;
import com.krishna.vaadin.grid.vos.Customer;
import com.krishna.vaadin.grid.vos.Customer.Gender;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.DateRenderer;

/**
 * @author Krishna
 *
 */
public class BasicGridView extends CssLayout implements MyView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4621381457091406226L;

	private Grid grid;

	public BasicGridView() {
		init();
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
	}

	private void init() {
		// Init the grid with Caption, alternatively can initialize the same
		// with passing the datasource
		grid = new Grid("My Basic Grid");
		grid.setContainerDataSource(getDatasource());
		initGridProperties();

		// Init the columns
		initGridColumns();
	}

	private void initGridProperties() {
		grid.setSelectionMode(SelectionMode.SINGLE);
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

	private Indexed getDatasource() {

		BeanItemContainer<Customer> container = new BeanItemContainer<Customer>(
				Customer.class);
		container.addAll(getCustomerList());
		return container;

	}

	private Set<Customer> getCustomerList() {
		Set<Customer> customers = new LinkedHashSet<Customer>();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 1987);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 22);
		for (int i = 1; i < 10; i++) {
			customers.add(new Customer(i, "Customer Name - " + i,
					cal.getTime(), "City - " + i, i % 2 == 0 ? Gender.Male
							: Gender.Female));
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		}
		return customers;
	}

	@Override
	public void attach() {
		super.attach();
		addComponent(grid);

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public boolean isCached() {
		return false;
	}

}
