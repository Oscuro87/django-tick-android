package org.ec.androidticket.activities.custom;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.squareup.otto.Bus;

import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.services.AuthService;
import org.ec.androidticket.backend.Async.services.TicketService;

public abstract class MyActionBarActivity extends ActionBarActivity
{
    protected Bus bus;
    protected AuthService authService;
    protected TicketService ticketService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bus = BusDepot.get().getBus(BusDepot.BusType.GENERAL);
        authService = AuthService.get();
        ticketService = TicketService.get();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        try
        {
            bus.register(this);
        } catch (IllegalArgumentException ignored)
        {
            Log.e("CustomLog", "Activity already registered to this bus.");
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        try
        {
            bus.unregister(this);
        } catch (IllegalArgumentException ignored)
        {
            Log.e("CustomLog", "Activity already unregistered from this bus.");
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        try
        {
            bus.unregister(this);
        } catch (IllegalArgumentException ignored)
        {
            Log.e("CustomLog", this.getClass().getCanonicalName() + " already unregistered.");
        }
    }
}
