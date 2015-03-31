package org.ec.androidticket.backend.Async.responses.login;

public class Login
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
        private String authtoken;
        private String first_name;
        private String last_name;
        private String email;
        private boolean staff;

        public Auth()
        {
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
            return staff;
        }

        @Override
        public String toString()
        {
            StringBuilder bld = new StringBuilder();
            bld.append("Email: ").append(email);
            bld.append("First name: ").append(first_name);
            bld.append("Last name: ").append(last_name);
            bld.append("Is staff? ").append(staff);
            return bld.toString();
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
