package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Building
{
    @SerializedName("pk")
    @Expose
    private int pk;
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

    public String getCountry()
    {
        return country;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getVicinity()
    {
        return vicinity;
    }

    public void setVicinity(String vicinity)
    {
        this.vicinity = vicinity;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getBuildingName()
    {
        return buildingName;
    }

    public void setBuildingName(String buildingName)
    {
        this.buildingName = buildingName;
    }

    public String getBuildingCode()
    {
        return buildingCode;
    }

}
