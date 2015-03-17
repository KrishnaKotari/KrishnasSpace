package com.krishna.vaadin.grid.basics;

import java.util.Set;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

/**
 * Init Home view, which consists of buttons to various views that this demo
 * show cases
 * 
 * @author Krishna
 *
 */
public class HomeView extends CssLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1401733255504381388L;

	public HomeView(Set<String> viewNames) {
		initViewButtons(viewNames);
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
	}

	/**
	 * Init Various View Buttons
	 * 
	 * @param viewNames
	 */
	private void initViewButtons(Set<String> viewNames) {

		for (String viewName : viewNames) {
			Button button = getViewButton(viewName);
			addComponent(button);
		}

	}

	/**
	 * Based on view Name returns view Buttons
	 * 
	 * @param viewName
	 * @return
	 */
	private Button getViewButton(final String viewName) {
		Button button = new Button(viewName);
		button.setImmediate(true);
		button.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7390868323331685789L;

			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().getNavigator().navigateTo(viewName);
			}
		});
		return button;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
