package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Company
{
    @SerializedName("pk")
    @Expose
    private Integer pk;

    @SerializedName("fk_suitableEventCategories")
    @Expose
    private List<Category> suitableEventCategories;

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

    @SerializedName("phone_number")
    @Expose
    private String phone;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("vat_number")
    @Expose
    private String VAT;

    public Company()
    {
    }

    public List<Category> getSuitableEventCategories()
    {
        return suitableEventCategories;
    }

    public void setSuitableEventCategories(List<Category> suitableEventCategories)
    {
        this.suitableEventCategories = suitableEventCategories;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
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

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVAT()
    {
        return VAT;
    }

    public void setVAT(String VAT)
    {
        this.VAT = VAT;
    }

    public Integer getPk()
    {
        return pk;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("suitableEventCategories=").append(suitableEventCategories);
        sb.append(", country='").append(country).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", vicinity='").append(vicinity).append('\'');
        sb.append(", postcode='").append(postcode).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", VAT='").append(VAT).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
