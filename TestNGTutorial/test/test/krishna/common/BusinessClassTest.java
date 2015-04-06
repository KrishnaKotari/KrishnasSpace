package test.krishna.common;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.krishna.common.BusinessClass;

/**
 * Test Class to unit test {@link BusinessClass}.
 * 
 * Note: Boundary tests are not done here
 * 
 * @author Krishna
 *
 */
public class BusinessClassTest {

	/**
	 * Initializes suite level stuff
	 */
	@BeforeSuite
	public void initSuite() {
		// This is one time operation
		System.setProperty("addOffset", "10");
	}

	/**
	 * Init all test class level stuff
	 */
	@BeforeClass
	public void init() {
		businessClass = new BusinessClass(20);
	}

	private BusinessClass businessClass;

	/**
	 * This method tests if the business method
	 * {@link BusinessClass#complicatedAddOp(Integer, Integer)} throws an error
	 * when invoked with null in first argument
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class }, groups = { "complicatedAddOp" })
	public void compliAddOpWithErrorInFirstArgTest() {
		// This method should throw an error
		businessClass.complicatedAddOp(null, 23);

	}

	/**
	 * Tests if the method returns correct value and it only runs if the method
	 * compliAddOpWithErrorInSecondArgTest returns positive
	 */
	@Test(groups = { "complicatedAddOp" }, dependsOnMethods = "compliAddOpWithErrorInSecondArgTest")
	public void myFirstCompliAddOpTest() {
		// Invoke actual Business method and get the result
		int actual = businessClass.complicatedAddOp(10, 23);
		// Check if the returned by the actual is equal to what is expected
		Assert.assertEquals(actual, 43);
	}

	/**
	 * This method tests if the business method
	 * {@link BusinessClass#complicatedAddOp(Integer, Integer)} throws an error
	 * when invoked with null in Second argument
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class }, groups = { "complicatedAddOp" })
	public void compliAddOpWithErrorInSecondArgTest() {
		// This method should throw an error
		businessClass.complicatedAddOp(23, null);

	}

	/**
	 * This tests run only if all tests related to "complicatedAddOp" group are
	 * positive
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class }, groups = "complicatedMathOp", dependsOnGroups = "complicatedAddOp")
	public void complicatedMathOpWithErrorInFirstArg() {
		// This method should throw an error
		businessClass.complicatedMathOpBasedOnAddOp(null, 23);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class }, groups = "complicatedMathOp", dependsOnGroups = "complicatedAddOp")
	public void complicatedMathOpWithErrorInSecondArg() {
		// This method should throw an error
		businessClass.complicatedMathOpBasedOnAddOp(23, null);
	}

	/**
	 * Tests if the method returns correct value
	 */
	@Test(groups = { "complicatedAddOp" }, dependsOnMethods = "compliAddOpWithErrorInSecondArgTest")
	public void myFirstComplicatedMathOpTest() {
		// Invoke actual Business method and get the result
		int actual = businessClass.complicatedMathOpBasedOnAddOp(10, 23);
		// Check if the returned by the actual is equal to what is expected
		Assert.assertEquals(actual, 73);
	}

}
