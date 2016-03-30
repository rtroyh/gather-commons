package com.gather.gathercommons.model.response;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 30-03-16
 * Time: 10:18
 */
public class NullResponse implements IResponse {
    @Override
    public String getMessage() {
        return "Null";
    }

    @Override
    public Outcome getOutcome() {
        return Outcome.NONE;
    }
}
