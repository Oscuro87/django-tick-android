package org.ec.androidticket.backend.models.ticketing;

public class Building
{
    private final String countryName;
    private final String address;
    private final String vicinity;
    private final String postcode;
    private final String buildingName;
    private final String buildingCode;
    private final boolean visible;

    public Building(String countryName, String address, String vicinity, String postcode, String buildingName, String buildingCode, boolean visible)
    {
        this.countryName = countryName;
        this.address = address;
        this.vicinity = vicinity;
        this.postcode = postcode;
        this.buildingName = buildingName;
        this.buildingCode = buildingCode;
        this.visible = visible;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getVicinity()
    {
        return vicinity;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public String getBuildingName()
    {
        return buildingName;
    }

    public String getBuildingCode()
    {
        return buildingCode;
    }

    public boolean isVisible()
    {
        return visible;
    }
}
