package org.ec.androidticket.activities.ticketDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.events.ticketEvents.FullTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.FullTicketSuccessResponseEvent;
import org.ec.androidticket.backend.Async.responses.FullTicketResponse;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.ticketing.FullTicket;

public class TicketDetailActivity extends ActionBarActivity
{
    private Integer ticketPK;
    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        bus = BusDepot.get().getBus(BusDepot.BusType.GENERAL);
        bus.register(this);

        Intent thisIntent = getIntent();
        Bundle params = thisIntent.getExtras();

        if (params != null)
        {
            this.ticketPK = params.getInt("ticketPK");
            bus.post(new FullTicketRequestEvent("Token " + UserDataCache.get().getAuthtoken(), ticketPK));
            Log.i("CustomLog", "Sent full ticket request event");
        } else
        {
            // TODO: Go back to home view
            Log.e("CustomLog", "Problem while requesting full ticket details for ticket pk: " + ticketPK);
        }
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    private void buildTicketViewFromTicketInstance(FullTicket inst)
    {
        // TODO: build ticket view from instance
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_ticket_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onTicketInformationsReceived(FullTicketSuccessResponseEvent event)
    {
        // TODO: Gérer la réponse du web service + check erreurs
        Log.i("CustomLog", "Received full ticket details loaded event");

    }
}
