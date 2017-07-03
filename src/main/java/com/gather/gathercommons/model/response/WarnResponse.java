package com.gather.gathercommons.model.response;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 7/3/17
 * Time: 16:57
 */
public class WarnResponse implements IResponse {
    private String message;

    public WarnResponse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Outcome getOutcome() {
        return Outcome.WARN;
    }
}
