package org.ec.androidticket.activities.home;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.Async.events.loginEvents.LoggedOutEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LogoutEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.SimpleTicketRequestEvent;
import org.ec.androidticket.backend.Async.services.TicketService;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.Async.services.AuthService;

public class TicketHomeActivity extends Activity
{
    private Bus eventBus;
    private AuthService authService;
    private TicketService ticketService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_home);

        Toast.makeText(getApplicationContext(), R.string.toast_more_options, Toast.LENGTH_SHORT).show();

        TextView header = (TextView)(findViewById(R.id.ticketHomeHeader));
        header.setText(header.getText() + " " + UserDataCache.get().getFullName());

        eventBus = new Bus();
        authService = new AuthService(eventBus);
        ticketService = new TicketService(eventBus);

        eventBus.post(new SimpleTicketRequestEvent(UserDataCache.get().getAuthtoken()));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        eventBus.unregister(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        try
        {
            eventBus.unregister(this);
        } catch (IllegalArgumentException ignored)
        {
            Log.e("CustomLog", this.getClass().getCanonicalName() + " already unregistered.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_ticket_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(item.getItemId())
        {
            case R.id.action_logout:
                eventBus.post(new LogoutEvent(UserDataCache.get().getAuthtoken()));
                return true;
            case R.id.action_createBuilding:
                // TODO: bouton create building
                return true;
        }

        if(item.getItemId() == R.id.action_logout)
        {
            eventBus.post(new LogoutEvent(UserDataCache.get().getAuthtoken()));
            return true; // consommé
        }


        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onLoggedOut(LoggedOutEvent event)
    {
        Context context = getApplicationContext();
        if(event.disconnected)
        {
            Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show();
            Log.i("CustomLost", "Logging out and back to main screen");
            NavUtils.navigateUpFromSameTask(this);
        }
        else
        {
            Toast.makeText(context, "Problem while logging out", Toast.LENGTH_SHORT).show();
        }
    }
}
