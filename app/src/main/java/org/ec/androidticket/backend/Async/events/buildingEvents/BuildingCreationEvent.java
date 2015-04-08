package org.ec.androidticket.backend.Async.events.buildingEvents;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.variants.NewBuilding;

public class BuildingCreationEvent
{
    private final String authtoken;
    private final NewBuilding building;

    public BuildingCreationEvent(String authtoken, NewBuilding building)
    {
        this.authtoken = "Token " + authtoken;
        this.building = building;
    }

    public String getAuthtoken()
    {
        return authtoken;
    }

    public NewBuilding getBuilding()
    {
        return building;
    }
}
