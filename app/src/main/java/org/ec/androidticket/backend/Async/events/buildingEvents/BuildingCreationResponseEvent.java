package org.ec.androidticket.backend.Async.events.buildingEvents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuildingCreationResponseEvent
{
    @SerializedName("success")
    @Expose
    public boolean success;

    @SerializedName("reason")
    @Expose
    public String reason;

    public BuildingCreationResponseEvent(boolean success, String reason)
    {
        this.success = success;
        this.reason = reason;
    }

    public BuildingCreationResponseEvent()
    {
    }
}
