package com.gather.gathercommons.model.messenger;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/14/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IMessenger {
    public MessageType getType();
    public String getMessage();
}
