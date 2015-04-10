package org.ec.androidticket.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.squareup.otto.Bus;

import org.ec.androidticket.backend.Async.BusDepot;

public abstract class MyFragment extends Fragment
{
    protected Bus bus;

    public MyFragment()
    {
        super();
        bus = BusDepot.get().getBus(BusDepot.BusType.GENERAL);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            bus.register(this);
        }
        catch(IllegalArgumentException ignored)
        {
            Log.e("CustomLog", "This fragment is already registered with the eventbus.");
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        try
        {
            bus.unregister(this);
        }
        catch(IllegalArgumentException ignored)
        {
            Log.e("CustomLog", "This fragment is already unregistered from the eventbus.");
        }
    }
}
