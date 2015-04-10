package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel
{
    @SerializedName("label")
    @Expose
    public String label;
}
