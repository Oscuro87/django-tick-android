package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Building
{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    @SerializedName("building_name")
    @Expose
    private String buildingName;
    @SerializedName("building_code")
    @Expose
    private String buildingCode;

    public Building()
    {
    }

    public Building(boolean dummyBuilding)
    {
        if(dummyBuilding)
        {
            this.id = -1;
            this.country = "";
            this.address = "";
            this.vicinity = "";
            this.postcode = "";
            this.buildingName = "";
            this.buildingCode = "";
        }
    }

    public int getId()
    {
        return id;
    }

    public String getCountry()
    {
        return country;
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

    @Override
    public String toString()
    {
        return buildingName;
    }
}
