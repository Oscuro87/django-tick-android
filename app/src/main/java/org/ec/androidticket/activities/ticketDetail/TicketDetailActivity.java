package org.ec.androidticket.activities.ticketDetail;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyFragmentActivity;
import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.events.ticketEvents.FullTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.FullTicketSuccessResponseEvent;
import org.ec.androidticket.backend.Async.responses.FullTicketResponse;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.ticketing.FullTicket;

import java.util.ArrayList;
import java.util.List;

/**
 * Vue détaillée d'un ticket, divisée en 3 parties:
 * La vue ticket contenant toutes ses informations
 * La vue commentaires qui permet de consulter ou écrire un nouveau commentaire
 * La vue historique ticket
 *
 * Tutorial pour viewpager : http://www.androidhive.info/2013/10/android-tab-layout-with-swipeable-views-1/
 */

public class TicketDetailActivity extends MyFragmentActivity implements ActionBar.TabListener
{
    private Integer ticketPK;
     // Contient tous les fragments utilisés comme "tab" dans le viewpager
    private List<Fragment> pagesFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
    {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)
    {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)
    {

    }
}
