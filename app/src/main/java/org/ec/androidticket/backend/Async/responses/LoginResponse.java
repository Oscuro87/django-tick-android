package org.ec.androidticket.backend.Async.responses;

import org.ec.androidticket.backend.models.ticketing.Auth;
import org.ec.androidticket.backend.models.ticketing.Logout;

public class LoginResponse
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

    public class LogoutResponse
    {
        int cod;
        private String base;
        private Logout logout;

        public int getCod()
        {
            return cod;
        }

        public String getBase()
        {
            return base;
        }

        public Logout getLogout()
        {
            return logout;
        }
    }
}
