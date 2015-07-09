/**
 * 
 */
package com.krishna.jaasexample.ui;

import javax.servlet.ServletException;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Krishna
 *
 */
@Theme("mytheme")
@Title("Login")
@PreserveOnRefresh
public class LoginUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6506152610760039424L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(VaadinRequest request) {
		Responsive.makeResponsive(this);
		setContent(getLoginView());
		addStyleName("loginview");

	}

	/**
	 * @return
	 */
	private Component getLoginView() {

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		Component loginForm = buildLoginForm();
		layout.addComponent(loginForm);
		layout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
		return layout;
	}

	public void logoutUser() {
		VaadinSession.getCurrent().getSession().invalidate();
		Page.getCurrent().reload();
	}

	private Component buildLoginForm() {
		final VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.setSpacing(true);
		Responsive.makeResponsive(loginPanel);
		loginPanel.addStyleName("login-panel");

		loginPanel.addComponent(buildLabels());
		loginPanel.addComponent(buildFields());
		// Dummy field, No functionality is added
		loginPanel.addComponent(new CheckBox("Remember me", true));
		return loginPanel;
	}

	private Component buildFields() {
		HorizontalLayout fields = new HorizontalLayout();
		fields.setSpacing(true);
		fields.addStyleName("fields");

		final TextField username = new TextField("Username");
		// Pre-populate for convenience
		username.setValue("admin");
		username.setIcon(FontAwesome.USER);
		username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

		final PasswordField password = new PasswordField("Password");
		password.setIcon(FontAwesome.LOCK);
		password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		password.setValue("admin@123");

		final Button signin = new Button("Sign In");
		signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
		signin.setClickShortcut(KeyCode.ENTER);
		signin.focus();

		fields.addComponents(username, password, signin);
		fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

		signin.addClickListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8816421044737741430L;

			@Override
			public void buttonClick(final ClickEvent event) {
				try {
					VaadinServletService.getCurrentServletRequest().login(
							username.getValue(), password.getValue());
				} catch (ServletException e) {
					Notification.show("Authentication Failed");
					return;
				}
				Page.getCurrent().setLocation("secured");
			}
		});
		return fields;
	}

	private Component buildLabels() {
		CssLayout labels = new CssLayout();
		labels.addStyleName("labels");

		Label welcome = new Label("Log in");
		welcome.setSizeUndefined();
		welcome.addStyleName(ValoTheme.LABEL_H4);
		welcome.addStyleName(ValoTheme.LABEL_COLORED);
		labels.addComponent(welcome);

		Label title = new Label("JAAS Demo");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H3);
		title.addStyleName(ValoTheme.LABEL_LIGHT);
		labels.addComponent(title);
		return labels;
	}

}
