package com.krishna.vaadin.grid;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.krishna.vaadin.grid.basics.BasicGridView;
import com.krishna.vaadin.grid.basics.EditableGrid;
import com.krishna.vaadin.grid.basics.FilterGrid;
import com.krishna.vaadin.grid.basics.FooterGrid;
import com.krishna.vaadin.grid.basics.GeneratedColumns;
import com.krishna.vaadin.grid.basics.HeaderGrid;
import com.krishna.vaadin.grid.basics.HomeView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("dark")
public class VaadinUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private String prevState = "";

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout mainLayout = new VerticalLayout();
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		CssLayout viewLayout = new CssLayout();
		Page.getCurrent().setTitle("Vaadin Demo");
		mainLayout.setSizeFull();
		viewLayout.setSizeFull();
		mainLayout.setMargin(true);
		setContent(mainLayout);
		mainLayout.addComponent(horizontalLayout);
		mainLayout.addComponent(viewLayout);
		mainLayout.setExpandRatio(viewLayout, 1f);
		Navigator navigator = new Navigator(this, viewLayout);
		setNavigator(navigator);
		setupHeader(horizontalLayout);
		Map<String, Class<? extends MyView>> myViews = getViewProvider();
		navigator.addView("", new HomeView(myViews.keySet()));
		navigator.addProvider(new CachedViewProvider(myViews));
	}

	private void setupHeader(HorizontalLayout horizontalLayout) {
		horizontalLayout.setHeight(60, Unit.PIXELS);
		horizontalLayout.setWidth(100, Unit.PERCENTAGE);
		final Button home = new Button("Home");
		home.setImmediate(true);
		home.addClickListener(getHomeClickLIstener(""));

		final Button back = new Button("Back");
		back.setImmediate(true);
		back.addClickListener(getBackClickLIstener());

		horizontalLayout.addComponent(home);

		horizontalLayout.addComponent(back);

		horizontalLayout.setComponentAlignment(home, Alignment.MIDDLE_LEFT);
		horizontalLayout.setComponentAlignment(back, Alignment.MIDDLE_RIGHT);

		addViewChangeListener(horizontalLayout);
	}

	private void addViewChangeListener(final HorizontalLayout horizontalLayout) {
		getNavigator().addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {
				if (event.getViewName().isEmpty()) {
					VaadinUI.this.prevState = "";
					horizontalLayout.setVisible(false);
				} else {
					horizontalLayout.setVisible(true);
				}
			}
		});
	}

	private ClickListener getBackClickLIstener() {
		return new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (null != VaadinUI.this.prevState) {
					getNavigator().navigateTo(VaadinUI.this.prevState);
				}
			}

		};
	}

	private Button.ClickListener getHomeClickLIstener(final String navigateTo) {
		return new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getNavigator().navigateTo(navigateTo);
			}

		};
	}

	private Map<String, Class<? extends MyView>> getViewProvider() {
		Map<String, Class<? extends MyView>> myViews = new LinkedHashMap<String, Class<? extends MyView>>();
		myViews.put("BasicGrid", BasicGridView.class);
		myViews.put("HeaderGrid", HeaderGrid.class);
		myViews.put("FooterGrid", FooterGrid.class);
		myViews.put("FilterGrid", FilterGrid.class);
		myViews.put("GeneratedColumns", GeneratedColumns.class);
		myViews.put("EditableGrid", EditableGrid.class);
		return myViews;
	}

}