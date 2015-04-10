package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.ec.androidticket.backend.models.ticketing.Building;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

public class RequestUserBuildingsResponseEvent
{
    @SerializedName("buildings")
    @Expose
    public final List<Building> userBuildings;

    public RetrofitError error;

    public RequestUserBuildingsResponseEvent(List<Building> userBuildings)
    {
        this.userBuildings = userBuildings;
        error = null;
    }

    public RequestUserBuildingsResponseEvent(RetrofitError error)
    {
        userBuildings = new ArrayList<>();
        this.error = error;
    }

    public boolean hasError()
    {
        return (this.error != null);
    }

    public List<Building> getUserBuildings()
    {
        return userBuildings;
    }
}
