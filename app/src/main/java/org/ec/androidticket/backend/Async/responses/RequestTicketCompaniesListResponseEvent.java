package org.ec.androidticket.backend.Async.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.ec.androidticket.backend.models.ticketing.Company;
import org.ec.androidticket.backend.models.ticketing.variants.SuitableCompany;

import java.util.List;

public class RequestTicketCompaniesListResponseEvent
{
    @SerializedName("companies")
    @Expose
    private List<SuitableCompany> companies;

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("success")
    @Expose
    private boolean success;

    public RequestTicketCompaniesListResponseEvent()
    {
    }

    public RequestTicketCompaniesListResponseEvent(String reason, boolean success)
    {
        this.companies = null;
        this.reason = reason;
        this.success = success;
    }

    public List<SuitableCompany> getCompanies()
    {
        return companies;
    }

    public String getReason()
    {
        return reason;
    }

    public boolean isSuccess()
    {
        return success;
    }
}
