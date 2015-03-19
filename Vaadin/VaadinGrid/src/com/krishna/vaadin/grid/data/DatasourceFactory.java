package com.krishna.vaadin.grid.data;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.krishna.vaadin.grid.data.Customer.Gender;
import com.krishna.vaadin.grid.data.VehicleInfo.Manufacturer;
import com.krishna.vaadin.grid.data.VehicleInfo.VehicleCategory;

/**
 * Factory class that Creates set of random data.
 * 
 * @author Krishna
 *
 */
public class DatasourceFactory {

	/**
	 * Returns a finite set of Customers
	 * 
	 * Used in Basic Grid
	 * 
	 * @param noOfRecords
	 *            noOfRecords that Needs to be retrieved.
	 * @return
	 */
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

	/**
	 * Returns set a Complex data set to show case Various grid operations
	 * 
	 * @return
	 */
	public static Set<VehicleInfo> getVehicleSalesDS() {
		Set<VehicleInfo> vehicles = new HashSet<>();
		Set<YearlySales> randomSalesData = getRandomSalesData();
		Map<Manufacturer, Set<String>> fourWheelerData = get4WheelerVehicleModels();
		Map<Manufacturer, Set<String>> twoWheelerData = get2WheelerVehicleModels();
		Iterator<YearlySales> salesDataIterator = randomSalesData.iterator();
		vehicles.addAll(getAssociatedData(fourWheelerData,
				VehicleCategory.FourWheeler, 0, salesDataIterator));
		vehicles.addAll(getAssociatedData(twoWheelerData,
				VehicleCategory.TwoWheeler, vehicles.size(), salesDataIterator));
		return vehicles;
	}

	/**
	 * method that populates the metadata to actual dataset
	 * 
	 * @param vehicularData
	 * @param category
	 * @param id
	 * @param salesDataIterator
	 * @return
	 */
	private static Set<VehicleInfo> getAssociatedData(
			Map<Manufacturer, Set<String>> vehicularData,
			VehicleCategory category, int id,
			Iterator<YearlySales> salesDataIterator) {
		Set<VehicleInfo> vehicles = new HashSet<>();
		for (Map.Entry<Manufacturer, Set<String>> manufacturerData : vehicularData
				.entrySet()) {
			Set<String> models = manufacturerData.getValue();
			Manufacturer manufacturer = manufacturerData.getKey();
			for (String model : models) {
				YearlySales sales2012 = salesDataIterator.next();
				YearlySales sales2013 = salesDataIterator.next();
				YearlySales sales2014 = salesDataIterator.next();
				VehicleInfo info = new VehicleInfo(id++, model, manufacturer,
						sales2013, sales2014, sales2012, category);
				vehicles.add(info);
			}
		}
		return vehicles;
	}

	/**
	 * Returns some random sales data
	 * 
	 * @return
	 */
	private static Set<YearlySales> getRandomSalesData() {
		Random random = new Random();

		Set<YearlySales> yearlySales = new HashSet<>();
		// Assuming there can't be more than 6 models
		for (int i = 0; i < (Manufacturer.values().length * 6 * 3); i++) {

			YearlySales sales = new YearlySales(random.nextInt(1000000),
					random.nextInt(1000000), random.nextInt(1000000),
					random.nextInt(1000000));
			yearlySales.add(sales);
		}
		System.out.println("YearlySales Data - " + yearlySales.size());
		return yearlySales;
	}

	/**
	 * Forms meta data for 4 wheeler Vehicle models
	 * 
	 * @return
	 */
	private static Map<Manufacturer, Set<String>> get4WheelerVehicleModels() {
		Map<Manufacturer, Set<String>> models = new HashMap<>();
		models.put(Manufacturer.Maruti,
				new HashSet<String>(Arrays.asList("Alto", "Swift", "Desire")));
		models.put(
				Manufacturer.Honda,
				new HashSet<String>(Arrays.asList("Honda City", "Accord",
						"Civic")));
		models.put(
				Manufacturer.Hyundai,
				new HashSet<String>(Arrays.asList("Santro", "i20", "i10",
						"Xcent")));
		models.put(Manufacturer.TataMotors,
				new HashSet<String>(Arrays.asList("Nano", "Safar", "indica")));

		return models;
	}

	/**
	 * Forms meta data for 2 wheeler Vehicle Models
	 * 
	 * @return
	 */
	private static Map<Manufacturer, Set<String>> get2WheelerVehicleModels() {
		Map<Manufacturer, Set<String>> models = new HashMap<>();

		models.put(Manufacturer.Bajaj,
				new HashSet<String>(Arrays.asList("Pulsar", "Discover")));
		models.put(
				Manufacturer.Honda,
				new HashSet<String>(Arrays.asList("Unicorn", "Stunner",
						"Activa", "Access")));
		models.put(
				Manufacturer.Hero,
				new HashSet<String>(Arrays
						.asList("Hunk", "Passion", "Splendor")));

		return models;
	}

}
