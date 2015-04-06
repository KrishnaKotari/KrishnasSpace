package com.krishna.common;

/**
 * 
 * @author Krishna
 *
 */
public class BusinessClass {

	private int offset;

	private int addOffset;

	/**
	 * 
	 * @param offset
	 */
	public BusinessClass(int offset) {
		String offsetStr = System.getProperty("addOffset");
		if (null != offsetStr) {
			this.addOffset = Integer.valueOf(offsetStr);
		}
		this.offset = offset;
	}

	/**
	 * Method to perform complicated add operation
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public Integer complicatedAddOp(Integer a, Integer b) {
		// Just throw an exception if either of them are null
		if (null == a || null == b) {
			throw new IllegalArgumentException("Variables cannot be null");
		}
		return a + b + addOffset;
	}

	/**
	 * Method that performs some complicated Math Op
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public Integer complicatedMathOpBasedOnAddOp(Integer a, Integer b) {

		return complicatedAddOp(offset, complicatedAddOp(a, b));

	}
}
