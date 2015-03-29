/**
 * 
 */
package com.krishna.vaadin.grid.basics;

import com.krishna.vaadin.grid.MyView;
import com.krishna.vaadin.grid.data.DatasourceFactory;
import com.krishna.vaadin.grid.data.VehicleInfo;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Krishna
 *
 */
public abstract class AbstractGridView extends VerticalLayout implements MyView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5214061818228626869L;

	protected Grid grid;

	private Label header;

	/**
	 * Default Constructor
	 */
	public AbstractGridView() {
		init();
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
		setSpacing(true);
		setImmediate(true);
	}

	protected abstract String getGridHeader();

	protected abstract String getBriefDesc();

	/**
	 * Init method that initalizes the content
	 */
	private void init() {
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
		setSpacing(true);
		setImmediate(true);
		initHeaderLabel();
		initGrid();
	}

	/**
	 * Init the header
	 */
	protected void initHeaderLabel() {
		header = new Label();
		header.setContentMode(ContentMode.HTML);
	}

	/**
	 * Initializes the grid
	 */
	protected void initGrid() {
		// Init the grid with Caption, alternatively can initialize the same
		// with passing the datasource
		grid = new Grid();
		grid.setContainerDataSource(getDatasource());
		grid.setSizeFull();
		grid.setImmediate(true);
		setDefaultGridProperties();
	}

	/**
	 * Sets the default properties of grid
	 */
	protected void setDefaultGridProperties() {

		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.removeColumn("id");
		grid.removeColumn("sales2012.totalSales");
		grid.removeColumn("sales2013.totalSales");
		grid.removeColumn("sales2014.totalSales");
		grid.setImmediate(true);
		grid.setHeightMode(HeightMode.CSS);
		for (Column column : grid.getColumns()) {
			column.setExpandRatio(1);
		}
	}

	protected void addComponentsToLayout() {

		addComponent(header);
		addComponent(grid);
		setExpandRatio(grid, 1);
		grid.setCaption(getGridHeader());
		header.setValue(getBriefDesc());
	}

	@Override
	public void attach() {
		super.attach();
		addComponentsToLayout();
	}

	/**
	 * 
	 * Initalizes the datasource
	 * 
	 * @return
	 */
	protected Indexed getDatasource() {
		BeanItemContainer<VehicleInfo> container = new BeanItemContainer<VehicleInfo>(
				VehicleInfo.class);
		container.addAll(DatasourceFactory.getVehicleSalesDS());
		container.addNestedContainerBean("sales2012");
		container.addNestedContainerBean("sales2013");
		container.addNestedContainerBean("sales2014");
		return container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
	 * .ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.MyView#isCached()
	 */
	@Override
	public boolean isCached() {
		// By Default Cache every view
		return true;
	}

}
