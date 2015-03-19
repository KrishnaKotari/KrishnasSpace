/**
 * 
 */
package com.krishna.vaadin.grid.basics;

import com.krishna.vaadin.grid.MyView;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;

/**
 * @author Krishna
 *
 */
public class HeaderGrid extends AbstractGridView implements MyView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6812549338221623673L;

	/**
	 * Default Constructor
	 */
	public HeaderGrid() {
		super();

		customizeGrid();
	}

	/**
	 * 
	 */
	private void customizeGrid() {
		grid.setFrozenColumnCount(3);
		HeaderRow mainHeaderRow = grid.prependHeaderRow();
		addNestedHeaderCell(mainHeaderRow, "sales2012", "2012");
		addNestedHeaderCell(mainHeaderRow, "sales2013", "2013");
		addNestedHeaderCell(mainHeaderRow, "sales2014", "2014");
	}

	private HeaderCell addNestedHeaderCell(HeaderRow mainHeaderRow,
			String baseYear, String mainCellText) {
		HeaderCell q1Header = mainHeaderRow.getCell(baseYear + ".q1");
		HeaderCell q2Header = mainHeaderRow.getCell(baseYear + ".q2");
		HeaderCell q3Header = mainHeaderRow.getCell(baseYear + ".q3");
		HeaderCell q4Header = mainHeaderRow.getCell(baseYear + ".q4");
		HeaderCell mainHeaderCell = mainHeaderRow.join(q1Header, q2Header,
				q3Header, q4Header);
		q1Header.setText("Quarter 1");
		q2Header.setText("Quarter 2");
		q3Header.setText("Quarter 3");
		q4Header.setText("Quarter 4");
		mainHeaderCell.setText(mainCellText);
		return mainHeaderCell;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.AbstractGridView#getGridHeader()
	 */
	@Override
	protected String getGridHeader() {
		return "Header Grid";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishna.vaadin.grid.basics.AbstractGridView#getBriefDesc()
	 */
	@Override
	protected String getBriefDesc() {
		return "Shows some of the advanced properties like Headers & Rows formatting";
	}

}
