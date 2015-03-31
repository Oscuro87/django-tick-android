package org.ec.androidticket.backend.Async.responses.helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User
{
    @Expose
    private Integer pk;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @Expose
    private String email;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("is_staff")
    @Expose
    private Boolean isStaff;

    /**
     * @return The pk
     */
    public Integer getPk()
    {
        return pk;
    }

    /**
     * @param pk The pk
     */
    public void setPk(Integer pk)
    {
        this.pk = pk;
    }

    /**
     * @return The firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName The first_name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return The lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName The last_name
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return The email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return The isActive
     */
    public Boolean getIsActive()
    {
        return isActive;
    }

    /**
     * @param isActive The is_active
     */
    public void setIsActive(Boolean isActive)
    {
        this.isActive = isActive;
    }

    /**
     * @return The isStaff
     */
    public Boolean getIsStaff()
    {
        return isStaff;
    }

    /**
     * @param isStaff The is_staff
     */
    public void setIsStaff(Boolean isStaff)
    {
        this.isStaff = isStaff;
    }

}
