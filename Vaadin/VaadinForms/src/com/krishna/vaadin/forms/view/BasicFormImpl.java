package com.krishna.vaadin.forms.view;

import java.util.Map;

import com.krishna.vaadin.forms.listeners.SubmitClickListener;
import com.krishna.vaadin.forms.vo.Employee;
import com.vaadin.data.Container;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

/**
 * 
 * @author Krishna
 *
 */
public class BasicFormImpl extends FormLayout implements BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2556018485572134408L;

	private BeanFieldGroup<Employee> fieldGroup;

	private SubmitClickListener<Employee> submitClickListener;

	/**
	 * 
	 */
	public BasicFormImpl() {
		setSizeFull();
		setMargin(true);
		setImmediate(true);
		// Initialize BeanFieldGroup
		fieldGroup = new BeanFieldGroup<Employee>(Employee.class);
		initComponents();
	}

	/**
	 * Init all components that needs to be displayed in the form
	 */
	private void initComponents() {
		addComponent(getTextField(NAME_CAPTION, NAME));
		addComponent(getComboBox(DEPT_CAPTION, DEPT, getDeptList()));
		addComponent(getTextField(CITY_CAPTION, CITY));
		addComponent(getDateField(DATE_OF_BIRTH_CAPTION, DATE_OF_BIRTH));
		addComponent(getCheckBox(IS_PERMANENT_EMPLOYEE_CAPTION,
				IS_PERMANENT_EMPLOYEE));
		addComponent(getButtonLayout());
		initCommitHandler();

	}

	/**
	 * Init the commit handler that gets invoked when Commit() method is called
	 */
	private void initCommitHandler() {
		fieldGroup.addCommitHandler(new CommitHandler() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2695561987381248384L;

			@Override
			public void preCommit(CommitEvent commitEvent)
					throws CommitException {
				// Check for validations
				fieldGroup.isValid();

			}

			@Override
			public void postCommit(CommitEvent commitEvent)
					throws CommitException {
				if (null != submitClickListener) {
					Employee bean = fieldGroup.getItemDataSource().getBean();
					submitClickListener.onSubmit(bean);
				}

			}
		});

	}

	/**
	 * 
	 * Initializes the Button layout that consist of Submit and reset
	 * 
	 * @return
	 */
	private Component getButtonLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.addComponent(getSubmitButton());
		buttonLayout.addComponent(getResetButton());
		return buttonLayout;
	}

	/**
	 * Initializes the Reset button
	 * 
	 * @return
	 */
	private Button getResetButton() {
		Button resetButton = getButton(RESET_CAPTION);
		resetButton.addClickListener(getResetClickListener());
		return resetButton;
	}

	/**
	 * Initializes the Reset click listener
	 * 
	 * @return
	 */
	private ClickListener getResetClickListener() {
		// TODO Auto-generated method stub
		return new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4846553077403712887L;

			@Override
			public void buttonClick(ClickEvent event) {
				resetForm();

			}
		};
	}

	/**
	 * @return
	 */
	private Button getSubmitButton() {
		Button submitButton = getButton(SUBMIT_CAPTION);
		submitButton.addClickListener(getSubmitButtonClickListener());
		return submitButton;
	}

	/**
	 * @return
	 */
	private ClickListener getSubmitButtonClickListener() {
		return new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4846553077403712887L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {

					fieldGroup.commit();
				} catch (CommitException e) {
					Map<Field<?>, InvalidValueException> invalidFields = e
							.getInvalidFields();
					// TODO handle it in a better way
					for (Map.Entry<Field<?>, InvalidValueException> invalidField : invalidFields
							.entrySet()) {
						((AbstractField<?>) invalidField.getKey())
								.setValidationVisible(true);
					}
					if (invalidFields.isEmpty()) {
						// TODO Handle this error
						e.printStackTrace();
						Notification.show("Save failed, Please try again");
					}

				}

			}
		};
	}

	/**
	 * @param caption
	 * @return
	 */
	private Button getButton(String caption) {
		Button button = new Button(caption);
		button.setImmediate(true);
		button.setHtmlContentAllowed(false);
		// TODO add some style
		return button;
	}

	/**
	 * @param caption
	 * @param propertyID
	 * @return
	 */
	private Component getCheckBox(String caption, String propertyID) {
		CheckBox checkBox = new CheckBox(caption);
		checkBox.setImmediate(true);
		fieldGroup.bind(checkBox, propertyID);
		checkBox.setValidationVisible(false);
		return checkBox;
	}

	/**
	 * @return
	 */
	private Container getDeptList() {
		IndexedContainer container = new IndexedContainer();
		container.addItem("Business Solutions");
		container.addItem("Business Support");
		container.addItem("BPO");
		container.addItem("Finance");
		container.addItem("IT");
		container.addItem("IT Ops");
		return container;
	}

	/**
	 * @param caption
	 * @param bindName
	 * @param container
	 *            TODO
	 * @return
	 */
	private ComboBox getComboBox(String caption, String bindName,
			Container container) {
		ComboBox comboBox = new ComboBox(caption);
		comboBox.setImmediate(true);
		comboBox.setValidationVisible(false);
		comboBox.setNewItemsAllowed(false);
		comboBox.setFilteringMode(FilteringMode.CONTAINS);
		comboBox.setNullSelectionAllowed(false);
		fieldGroup.bind(comboBox, bindName);
		comboBox.setContainerDataSource(container);
		return comboBox;
	}

	/**
	 * @param caption
	 * @param bindName
	 * @return
	 */
	private DateField getDateField(String caption, String bindName) {
		DateField dateField = new DateField(caption);
		dateField.setImmediate(true);
		dateField.setValidationVisible(false);
		dateField.setDateFormat(DATE_FORMAT);
		fieldGroup.bind(dateField, bindName);
		return dateField;
	}

	/**
	 * By default you can use the fields that are generated by
	 * {@link BeanFieldGroup} but in case you require something to be customized
	 * then this is how you initialize
	 * 
	 * @param caption
	 * @param bindName
	 * @return
	 */
	private TextField getTextField(String caption, String bindName) {
		TextField textField = new TextField(caption);
		textField.setImmediate(true);
		fieldGroup.bind(textField, bindName);
		textField.setValidationVisible(false);
		textField.setNullRepresentation(TEXT_NULL_REPRESENTATION);
		return textField;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.krishna.vaadin.forms.BasicForm#setDatasource(com.krishna.vaadin.forms
	 * .vo.Employee)
	 */
	@Override
	public void setDatasource(Employee employee) {
		fieldGroup.setItemDataSource(employee);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.forms.view.BasicForm#resetForm()
	 */
	@Override
	public void resetForm() {
		fieldGroup.discard();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.krishna.vaadin.forms.view.BasicForm#setFormSubmitHandler(com.krishna
	 * .vaadin.forms.listeners.SubmitClickListener)
	 */
	@Override
	public void setFormSubmitHandler(
			SubmitClickListener<Employee> submitClickListener) {
		this.submitClickListener = submitClickListener;
	}

}
