package org.ec.androidticket.backend.Async.responses;

public class ErrorResponse
{
    private int code;
    private String base;
    private ErrorResponse errorResponse;

    public ErrorResponse(int code, String base, ErrorResponse errorResponse)
    {
        this.code = code;
        this.base = base;
        this.errorResponse = errorResponse;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getBase()
    {
        return base;
    }

    public void setBase(String base)
    {
        this.base = base;
    }

    public ErrorResponse getErrorResponse()
    {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse)
    {
        this.errorResponse = errorResponse;
    }
}
