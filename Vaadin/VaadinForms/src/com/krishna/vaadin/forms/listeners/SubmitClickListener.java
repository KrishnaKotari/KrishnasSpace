/**
 * 
 */
package com.krishna.vaadin.forms.listeners;

/**
 * @author Krishna
 *
 */
public interface SubmitClickListener<T> {

	/**
	 * Method to invoke on click of submit
	 * 
	 * @param bean
	 */
	void onSubmit(T bean);

}
