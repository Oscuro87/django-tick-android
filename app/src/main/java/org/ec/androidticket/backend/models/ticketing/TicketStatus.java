package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;

public class TicketStatus
{
    @Expose
    private Integer pk;
    @Expose
    private String label;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
