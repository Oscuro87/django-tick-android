package org.ec.androidticket.backend.services;

import android.app.Activity;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.backend.Async.RESTClient;
import org.ec.androidticket.backend.Async.responses.RestLoginResponses;
import org.ec.androidticket.backend.events.loginEvents.LoggedOutEvent;
import org.ec.androidticket.backend.events.loginEvents.LoginEvent;
import org.ec.androidticket.backend.events.loginEvents.LoginFailureEvent;
import org.ec.androidticket.backend.events.loginEvents.LoginSuccessEvent;
import org.ec.androidticket.backend.events.loginEvents.LogoutEvent;
import org.ec.androidticket.backend.events.loginEvents.LogoutFailEvent;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AuthService
{
    private Activity attachedActivity;
    private Bus bus;
    private LogoutEvent logoutEvent;
    private LoginEvent loginEvent;

    public AuthService(Bus bus, Activity attachedActivity)
    {
        this.attachedActivity = attachedActivity;
        this.bus = bus;
        this.bus.register(this);
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event)
    {
        RESTClient.get().authTicket(event.getEmail(), event.getPassword(), new Callback<RestLoginResponses.Auth>()
        {
            @Override
            public void success(RestLoginResponses.Auth auth, Response response)
            {
                if (auth.isSuccess())
                {
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
        RESTClient.get().logoutTicket(
                "Token " + event.getAuthtoken(),
                new Callback<RestLoginResponses.Logout>()
                {
                    @Override
                    public void success(RestLoginResponses.Logout logout, Response response)
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
