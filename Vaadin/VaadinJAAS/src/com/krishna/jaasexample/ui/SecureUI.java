/**
 * 
 */
package com.krishna.jaasexample.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Krishna
 *
 */
@Theme("mytheme")
@Title("Secured UI")
@PreserveOnRefresh
public class SecureUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6306493315286395679L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout mainLayout = new HorizontalLayout();
		Label title = new Label("Secured UI");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H3);
		title.addStyleName(ValoTheme.LABEL_COLORED);
		title.addStyleName(ValoTheme.LABEL_BOLD);
		mainLayout.addComponent(title);
		Button button = new Button("Signout");
		button.addStyleName(ValoTheme.BUTTON_PRIMARY);
		mainLayout.setSizeUndefined();
		mainLayout.setWidth(100, Unit.PERCENTAGE);
		mainLayout.addComponent(button);
		mainLayout.setMargin(true);
		button.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -3396042012424022430L;

			@Override
			public void buttonClick(ClickEvent event) {

				VaadinSession.getCurrent().getSession().invalidate();
				Page.getCurrent().reload();

			}
		});
		mainLayout.setComponentAlignment(button, Alignment.TOP_RIGHT);
		setContent(mainLayout);

	}

}
