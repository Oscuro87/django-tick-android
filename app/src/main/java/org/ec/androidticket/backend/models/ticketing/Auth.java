package org.ec.androidticket.backend.models.ticketing;

public class Auth
{
    private int pk;
    private String authtoken;
    private String first_name;
    private String last_name;
    private String email;
    private boolean is_staff;
    private boolean is_active;

    public Auth()
    {
    }

    public int getPk()
    {
        return pk;
    }

    public String getAuthtoken()
    {
        return authtoken;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public String getEmail()
    {
        return email;
    }

    public boolean isStaff()
    {
        return is_staff;
    }

    public boolean isActive()
    {
        return is_active;
    }

    @Override
    public String toString()
    {
        StringBuilder bld = new StringBuilder();
        bld.append("Email: ").append(email).append("\n");
        bld.append("First name: ").append(first_name).append("\n");
        bld.append("Last name: ").append(last_name).append("\n");
        bld.append("Is banned?").append(!is_active).append("\n");
        bld.append("Is is_staff? ").append(is_staff);
        return bld.toString();
    }
}
