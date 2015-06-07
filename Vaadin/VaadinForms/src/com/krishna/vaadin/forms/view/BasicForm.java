/**
 * 
 */
package com.krishna.vaadin.forms.view;

import com.krishna.vaadin.forms.listeners.SubmitClickListener;
import com.krishna.vaadin.forms.vo.Employee;
import com.vaadin.ui.Component;

/**
 * @author Krishna
 *
 */
public interface BasicForm extends Component {

	String NAME = "name";

	String DEPT = "dept";

	String CITY = "city";

	String IS_PERMANENT_EMPLOYEE = "permanentEmployee";

	String DATE_OF_BIRTH = "dateOfBirth";

	String NAME_CAPTION = "Name";

	String DEPT_CAPTION = "Department";

	String CITY_CAPTION = "City";

	String IS_PERMANENT_EMPLOYEE_CAPTION = "Is Permanent Employee?";

	String DATE_OF_BIRTH_CAPTION = "Date of Birth";

	String SUBMIT_CAPTION = "Submit";

	String RESET_CAPTION = "Reset";

	String TEXT_NULL_REPRESENTATION = "";

	String DATE_FORMAT = "dd-MM-yyyy";

	void setDatasource(Employee employee);

	void setFormSubmitHandler(SubmitClickListener<Employee> submitClickListener);

	void resetForm();

}
