package com.gather.gathercommons.model.response;

import com.gather.gathercommons.model.DefaultListModel;
import com.gather.gathercommons.model.IDataTableModel;
import com.gather.gathercommons.model.IListModel;
import com.gather.gathercommons.util.Validator;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 29-03-16
 * Time: 15:48
 */
public class DefaultResponse implements IResponse {
    private IListModel model;

    public DefaultResponse(IListModel model) {
        this.model = model;
    }

    public DefaultResponse(IDataTableModel model) {
        IListModel model1 = new DefaultListModel();
        model1.setRows(model.getTitles());
        this.model = model1;
    }

    public String getMessage() {
        if (model != null) {
            if (Validator.validateList(model.getRows()) && Validator.validateList(model.getRows().get(0)) && Validator.validateString(model.getRows().get(0).get(1))) {
                return model.getRows().get(0).get(1).toString().trim();
            }
        }

        return "";
    }

    public Outcome getOutcome() {
        if (model != null) {
            if (Validator.validateList(model.getRows()) && Validator.validateList(model.getRows().get(0))) {
                if (model.getRows().get(0).get(0).equals(0)) {
                    return Outcome.OK;
                }

                return Outcome.ERROR;
            }
        }

        return Outcome.NONE;
    }
}
