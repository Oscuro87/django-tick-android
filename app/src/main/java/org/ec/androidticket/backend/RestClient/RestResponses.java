package org.ec.androidticket.backend.RestClient;

import org.ec.androidticket.backend.models.UserData;

public class RestResponses
{
    public class AuthResponse
    {
        int cod;
        private String base;
        private Auth auth;

        public int getCod()
        {
            return cod;
        }

        public String getBase()
        {
            return base;
        }

        public Auth getAuth()
        {
            return auth;
        }
    }

    public class Auth
    {
        private boolean success;
        private String reason;
        private String first_name;
        private String last_name;
        private String email;
        private boolean staff;

        private final UserData userDataObject;

        public Auth()
        {
            super();
            this.userDataObject = new UserData(email, first_name, last_name, staff);
        }

        public boolean isSuccess()
        {
            return success;
        }

        public String getReason()
        {
            return reason;
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
            return staff;
        }

        public UserData getUserDataObject()
        {
            return userDataObject;
        }
    }

    public class LogoutResponse
    {
        int cod;
        private String base;
        private Logout logout;
    }

    public class Logout
    {
        private boolean disconnected;

        public boolean isDisconnected()
        {
            return disconnected;
        }
    }
}
