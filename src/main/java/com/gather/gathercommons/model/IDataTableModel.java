package com.gather.gathercommons.model;

import java.util.List;

public interface IDataTableModel extends IListModel {
    List<List<Object>> getHeaders();

    List<List<Object>> getTitles();

    void setTitles(List<List<Object>> titles);

    void setHeaders(List<List<Object>> header);
}
