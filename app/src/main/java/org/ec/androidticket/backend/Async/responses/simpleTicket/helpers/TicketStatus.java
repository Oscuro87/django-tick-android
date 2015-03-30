package org.ec.androidticket.backend.Async.responses.simpleTicket.helpers;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class TicketStatus
{

    @Expose
    private Integer pk;
    @Expose
    private String label;

    private TicketStatus(Parcel input)
    {
        pk = input.readInt();
        label = input.readString();
    }

    public Integer getPk()
    {
        return pk;
    }

    public void setPk(Integer pk)
    {
        this.pk = pk;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
}
