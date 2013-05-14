package com.gather.gathercommons.model.messenger;

import com.gather.gathercommons.model.IListModel;
import com.gather.gathercommons.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/8/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public final class Messenger implements IMessenger,
                                        IListModel {
    private List<List<Object>> rows;
    private String message;

    @Override
    public List<List<Object>> getRows() {
        if (this.rows == null) {
            this.rows = new ArrayList<List<Object>>();
        }

        return rows;
    }

    @Override
    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public MessageType getType() {
        final List<List<Object>> rows1 = this.getRows();

        if (Validator.validateList(rows1)) {
            final Object messageTypeID = rows1.get(0).get(0);

            if (messageTypeID instanceof Number) {
                if (messageTypeID.toString().equals("0")) {
                    return MessageType.ERROR;
                }
            }
        }

        return MessageType.INFO;
    }

    public final String getMessage() {
        return message;
    }

    public final void cleanMessage() {
        this.message = null;
    }
}
