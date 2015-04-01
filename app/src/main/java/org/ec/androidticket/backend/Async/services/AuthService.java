package org.ec.androidticket.backend.Async.services;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.RESTClient;
import org.ec.androidticket.backend.Async.events.loginEvents.LoggedOutEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginFailureEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginSuccessEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LogoutEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LogoutFailEvent;
import org.ec.androidticket.backend.models.ticketing.Auth;
import org.ec.androidticket.backend.models.ticketing.Logout;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AuthService
{
    private static AuthService instance = null;
    private Bus bus;

    private AuthService()
    {
        this.bus = BusDepot.get().getBus(BusDepot.BusType.GENERAL);
        this.bus.register(this);
    }

    public static AuthService get()
    {
        if(instance == null)
            instance = new AuthService();
        return instance;
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event)
    {
        Log.i("CustomLog", "Received login event");
        postLoginAnswer(event.getEmail(), event.getPassword());
    }

    private void postLoginAnswer(String email, String password)
    {
        RESTClient.getLoginAPI().authTicket(
                email,
                password,
                new Callback<Auth>()
                {
                    @Override
                    public void success(Auth auth, Response response)
                    {
                        Log.i("CustomLog", auth.toString());
                        bus.post(new LoginSuccessEvent(
                                auth.getAuthtoken(),
                                auth.getFirst_name(),
                                auth.getLast_name(),
                                auth.getEmail(),
                                auth.isStaff(),
                                auth.isActive()
                        ));
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
        postLogoutAnswer(event.getAuthtoken());
    }

    private void postLogoutAnswer(String authtoken)
    {
        RESTClient.getLoginAPI().logoutTicket(
                "Token " + authtoken,
                new Callback<Logout>()
                {
                    @Override
                    public void success(Logout logout, Response response)
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
