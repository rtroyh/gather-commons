package com.gather.gathercommons.model;

import java.util.ArrayList;
import java.util.List;

public class DefaultDataTableModel implements IDataTableModel {

	private List<Object> selectedRow;

	private List<List<Object>> titles;
	private List<List<Object>> headers;
	private List<List<Object>> rows;

	public DefaultDataTableModel() {
		super();
	}

	public DefaultDataTableModel(	List<List<Object>> titles,
									List<List<Object>> headers,
									List<List<Object>> rows) {
		super();
		this.titles = titles;
		this.headers = headers;
		this.rows = rows;
	}

	@Override
	public List<List<Object>> getHeaders() {
		if (this.headers == null) {
			this.headers = new ArrayList<List<Object>>();
		}

		return headers;
	}

	@Override
	public List<List<Object>> getRows() {
		if (this.rows == null) {
			this.rows = new ArrayList<List<Object>>();
		}

		return rows;
	}

	@Override
	public List<List<Object>> getTitles() {
		if (this.titles == null) {
			this.titles = new ArrayList<List<Object>>();
		}

		return titles;
	}

	@Override
	public void setTitles(List<List<Object>> titles) {
		this.titles = titles;
	}

	@Override
	public void setHeaders(List<List<Object>> headers) {
		this.headers = headers;
	}

	@Override
	public void setRows(List<List<Object>> rows) {
		this.rows = rows;
	}

	@Override
	public List<Object> getSelectedRow() {
		return this.selectedRow;
	}

	@Override
	public void setSelectedRow(List<Object> row) {
		this.selectedRow = row;
	}

}
