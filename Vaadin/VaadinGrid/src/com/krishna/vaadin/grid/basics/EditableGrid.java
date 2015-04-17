/**
 * 
 */
package com.krishna.vaadin.grid.basics;

import java.util.Arrays;
import java.util.Collection;

import com.krishna.vaadin.grid.data.VehicleInfo;
import com.krishna.vaadin.grid.data.VehicleInfo.Manufacturer;
import com.krishna.vaadin.grid.data.VehicleInfo.VehicleCategory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * @author Krishna
 *
 */
public class EditableGrid extends AbstractGridView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6061549461509228639L;

	/**
	 * 
	 */
	public EditableGrid() {
		super();
		// This enables grid editing
		grid.setEditorEnabled(true);
		setUpGridEditorProperties();
	}

	/**
	 * 
	 */
	private void setUpGridEditorProperties() {
		// Setting the field group
		grid.setEditorFieldGroup(getFieldGroup());
		grid.getColumn("manufacturer").setEditorField(
				getComboBox("Manufacturer is required!",
						Arrays.asList(Manufacturer.values())));

		grid.getColumn("category").setEditorField(
				getComboBox("Vehicle type is required!",
						Arrays.asList(VehicleCategory.values())));
	}

	/**
	 * @return
	 */
	private Field<?> getComboBox(String requiredErrorMsg, Collection<?> items) {
		ComboBox comboBox = new ComboBox();
		comboBox.setNullSelectionAllowed(true);
		IndexedContainer container = new IndexedContainer(items);
		comboBox.setContainerDataSource(container);
		comboBox.setRequired(true);
		comboBox.setRequiredError(requiredErrorMsg);
		return comboBox;
	}

	/**
	 * @return
	 */
	private FieldGroup getFieldGroup() {
		BeanFieldGroup<VehicleInfo> beanFieldGroup = new BeanFieldGroup<VehicleInfo>(
				VehicleInfo.class);
		beanFieldGroup.addCommitHandler(new CommitHandler() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6062316515368687380L;

			@Override
			public void preCommit(CommitEvent commitEvent)
					throws CommitException {

			}

			@Override
			public void postCommit(CommitEvent commitEvent)
					throws CommitException {

				Notification.show("Saved Successfully!!",
						Type.TRAY_NOTIFICATION);

			}
		});

		return beanFieldGroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.AbstractGridView#getGridHeader()
	 */
	@Override
	protected String getGridHeader() {
		return "Editable Grid";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.AbstractGridView#getBriefDesc()
	 */
	@Override
	protected String getBriefDesc() {
		return "Grid helps in understanding the key concepts in creating a editable grid.";
	}

}
