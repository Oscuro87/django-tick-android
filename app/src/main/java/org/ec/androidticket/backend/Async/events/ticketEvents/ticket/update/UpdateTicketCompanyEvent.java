package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update;

public class UpdateTicketCompanyEvent
{
    private final String authtoken;
    private final String ticketCode;
    private final int companyPK;

    public UpdateTicketCompanyEvent(String authtoken, String ticketCode, int companyPK)
    {
        this.authtoken = "Token " + authtoken;
        this.ticketCode = ticketCode;
        this.companyPK = companyPK;
    }

    public String getAuthtoken()
    {
        return authtoken;
    }

    public String getTicketCode()
    {
        return ticketCode;
    }

    public int getCompanyPK()
    {
        return companyPK;
    }
}
