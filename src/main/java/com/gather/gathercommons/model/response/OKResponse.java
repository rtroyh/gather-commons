package com.gather.gathercommons.model.response;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 23-05-16
 * Time: 18:17
 */
public class OKResponse implements IResponse {
    private String message;

    public OKResponse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Outcome getOutcome() {
        return Outcome.OK;
    }
}
