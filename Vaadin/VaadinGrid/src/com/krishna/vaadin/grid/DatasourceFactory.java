package com.krishna.vaadin.grid;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

import com.krishna.vaadin.grid.vos.Customer;
import com.krishna.vaadin.grid.vos.Customer.Gender;

/**
 * 
 * @author Krishna
 *
 */
public class DatasourceFactory {

	public static Set<Customer> getCustomerDatasource(int noOfRecords) {

		Set<Customer> customers = new LinkedHashSet<Customer>();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 1987);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 22);
		for (int i = 1; i < noOfRecords; i++) {
			customers.add(new Customer(i, "Customer Name - " + i,
					cal.getTime(), "City - " + i, i % 2 == 0 ? Gender.Male
							: Gender.Female));
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		}
		return customers;
	}

}
