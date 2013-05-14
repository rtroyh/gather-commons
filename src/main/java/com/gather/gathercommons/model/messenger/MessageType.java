package com.gather.gathercommons.model.messenger;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/14/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public enum MessageType {
    ERROR(1,
          "ERROR"),
    WARNING(2,
            "WARNING"),
    INFO(3,
         "INFO");

    private int id;
    private String alias;

    MessageType(int id,
                String alias) {
        this.id = id;
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }
}
