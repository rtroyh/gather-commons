package com.gather.gathercommons.model.response;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 27-04-16
 * Time: 16:48
 */
public class ErrorResponse implements IResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Outcome getOutcome() {
        return Outcome.ERROR;
    }
}
