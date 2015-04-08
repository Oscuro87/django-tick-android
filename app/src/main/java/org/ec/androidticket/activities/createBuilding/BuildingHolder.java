package org.ec.androidticket.activities.createBuilding;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.variants.NewBuilding;

public class BuildingHolder
{
    public String country;
    public String address;
    public String postcode;
    public String vicinity;
    public String buildingName;

    public NewBuilding buildBuilding()
    {
        NewBuilding nb = new NewBuilding();
        nb.country = country;
        nb.address = address;
        nb.postcode = postcode;
        nb.vicinity = vicinity;
        nb.buildingName = buildingName;
        return nb;
    }
}
