package com.gather.gathercommons.model;

import java.util.List;

public interface IDataTableModel {

    public List<List<Object>> getHeaders();

    public List<List<Object>> getRows();

    public List<List<Object>> getTitles();

    public void setTitles(List<List<Object>> titles);

    public void setHeaders(List<List<Object>> header);

    public void setRows(List<List<Object>> rows);

    public List<Object> getSelectedRow();

    public void setSelectedRow(List<Object> row);
}
