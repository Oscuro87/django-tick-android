package org.ec.androidticket.activities.tickets;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.busEvents.LoggedOutEvent;
import org.ec.androidticket.backend.busEvents.LogoutEvent;
import org.ec.androidticket.backend.models.internal.UserData;
import org.ec.androidticket.backend.services.AuthService;

public class TicketHomeActivity extends ActionBarActivity
{
    private Bus eventBus;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_home);

        Toast.makeText(getApplicationContext(), R.string.toast_more_options, Toast.LENGTH_SHORT).show();

        eventBus = new Bus();
        authService = new AuthService(eventBus, this);
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

        if(item.getItemId() == R.id.action_logout)
        {
            eventBus.post(new LogoutEvent(UserData.get().getSessionid()));
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
