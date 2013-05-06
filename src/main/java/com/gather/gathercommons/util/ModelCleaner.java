package com.gather.gathercommons.util;

import com.gather.gathercommons.model.IComboBoxModel;
import com.gather.gathercommons.model.IDataTableModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 3/26/13
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class ModelCleaner {

    public static void cleanModel(IDataTableModel model) {
        if (model != null) {
            if (model.getTitles() != null) {
                model.getTitles().clear();
                model.setTitles(null);
            }

            if (model.getHeaders() != null) {
                model.getHeaders().clear();
                model.setHeaders(null);
            }

            if (model.getRows() != null) {
                model.getRows().clear();
                model.setRows(null);
            }

            if (model.getSelectedRow() != null) {
                model.getSelectedRow().clear();
                model.setSelectedRow(null);
            }
        }
    }

    public static void cleanModel(IComboBoxModel model) {
        if (model != null) {
            if (model.getOptions() != null) {
                model.getOptions().clear();
                model.setOptions(null);
            }

            final Object selectedOption = model.getSelectedOption();
            if (selectedOption != null) {
                if (selectedOption instanceof List)
                ((List) selectedOption).clear();
                model.setSelectedOption(null);
            }
        }
    }
}
