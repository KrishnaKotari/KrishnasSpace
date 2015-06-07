package com.krishna.vaadin.forms;

import javax.servlet.annotation.WebServlet;

import com.krishna.vaadin.forms.listeners.SubmitClickListener;
import com.krishna.vaadin.forms.view.BasicForm;
import com.krishna.vaadin.forms.view.BasicFormImpl;
import com.krishna.vaadin.forms.vo.Employee;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("vaadinforms")
public class VaadinformsUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinformsUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		BasicForm basicForm = new BasicFormImpl();
		basicForm.setFormSubmitHandler(getSubmitClickListener());
		basicForm.setDatasource(new Employee());
		setContent(basicForm);
	}

	/**
	 * @return
	 */
	private SubmitClickListener<Employee> getSubmitClickListener() {
		return new SubmitClickListener<Employee>() {

			@Override
			public void onSubmit(Employee bean) {

				Notification.show("Saved Employee - " + bean.getName());

			}
		};
	}

}