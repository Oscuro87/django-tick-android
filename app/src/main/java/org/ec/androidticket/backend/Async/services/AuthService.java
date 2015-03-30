package org.ec.androidticket.backend.Async.services;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.backend.Async.RESTClient;
import org.ec.androidticket.backend.Async.events.loginEvents.LoggedOutEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginFailureEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginSuccessEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LogoutEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LogoutFailEvent;
import org.ec.androidticket.backend.Async.responses.login.Login;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AuthService
{
    private Bus bus;

    public AuthService(Bus bus)
    {
        this.bus = bus;
        this.bus.register(this);
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event)
    {
        RESTClient.getLoginAPI().authTicket(event.getEmail(), event.getPassword(), new Callback<Login.Auth>()
        {
            @Override
            public void success(Login.Auth auth, Response response)
            {
                if (auth.isSuccess())
                {
                    Log.i("CustomLog", auth.toString());
                    bus.post(new LoginSuccessEvent(
                            auth.getAuthtoken(),
                            auth.isSuccess(),
                            auth.getReason(),
                            auth.getFirst_name(),
                            auth.getLast_name(),
                            auth.getEmail(),
                            auth.isStaff()
                    ));
                } else
                {
                    bus.post(new LoginFailureEvent(auth.getReason()));
                }
            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.e("CustomLog", "Failure in onUserLogin in AuthService:\n" + error.toString());
                bus.post(new LoginFailureEvent("Failure in onUserLogin in AuthService:\n" + error.toString()));
            }
        });
    }

    @Subscribe
    public void onLogoutEvent(LogoutEvent event)
    {
        RESTClient.getLoginAPI().logoutTicket(
                "Token " + event.getAuthtoken(),
                new Callback<Login.Logout>()
                {
                    @Override
                    public void success(Login.Logout logout, Response response)
                    {
                        bus.post(new LoggedOutEvent(logout.isDisconnected()));
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Log.e("CustomLog", "Logout unsuccessful: " + error.toString());
                        bus.post(new LogoutFailEvent(error.toString()));
                    }
                });
    }
}
