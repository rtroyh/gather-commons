package com.gather.gathercommons.model.messenger;

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
public final class Messenger implements IMessenger {
    private List<Object> row;

    public List<Object> getRow() {
        if (this.row == null) {
            this.row = new ArrayList<Object>();
        }

        return row;
    }

    public void setRow(List<Object> row) {
        this.row = row;
    }

    public MessageType getType() {
        final List<Object> rows = this.getRow();

        if (Validator.validateList(rows)) {
            final Object messageTypeID = rows.get(0);

            if (messageTypeID instanceof Number) {
                if (messageTypeID.toString().equals("0")) {
                    return MessageType.ERROR;
                }
            }
        }

        return MessageType.INFO;
    }

    public final String getMessage() {
        final List<Object> row = this.getRow();

        if (Validator.validateList(row)) {
            final Object message = row.get(1);

            if (message instanceof String) {
                return message.toString();
            }
        }

        return "";
    }
}
