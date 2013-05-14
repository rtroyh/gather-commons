package com.gather.gathercommons.model;

import java.util.List;

public interface IDataTableModel extends IListModel {
    public List<List<Object>> getHeaders();

    public List<List<Object>> getTitles();

    public void setTitles(List<List<Object>> titles);

    public void setHeaders(List<List<Object>> header);

    public List<Object> getSelectedRow();

    public void setSelectedRow(List<Object> row);
}
