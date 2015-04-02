package org.ec.androidticket.activities.ticketDetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyActionBarActivity;
import org.ec.androidticket.activities.ticketDetail.adapters.TicketDetailPagerAdapter;
import org.ec.androidticket.activities.ticketDetail.fragments.TicketDetailFragment;
import org.ec.androidticket.backend.Async.events.ticketEvents.FullTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.FullTicketSuccessResponseEvent;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.ticketing.FullTicket;

import java.util.ArrayList;
import java.util.List;

/**
 * Vue détaillée d'un ticket, divisée en 3 parties:
 * La vue ticket contenant toutes ses informations
 * La vue commentaires qui permet de consulter ou écrire un nouveau commentaire
 * La vue historique ticket
 * Tutorial pour viewpager : http://www.androidhive.info/2013/10/android-tab-layout-with-swipeable-views-1/
 */

public class TicketDetailActivity extends MyActionBarActivity implements android.support.v7.app.ActionBar.TabListener, TicketDetailFragment.OnTicketDetailFragmentInteractionListener
{
    private Integer ticketPK;
    // Fragments utilisés pour les "tabs"
    private List<String> tabNames;

    private ViewPager viewPager;
    private TicketDetailPagerAdapter pagerAdapter;
    private android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        if(!unpackParameters())
        {
            // TODO: problème au chargement du ticket -> retourner sur la vue tickets d'ensemble
        }

        setupFragments();
        setupPager();
    }

    private boolean unpackParameters()
    {
        Intent thisIntent = getIntent();
        Bundle params = thisIntent.getExtras();

        if (params != null)
        {
            this.ticketPK = params.getInt("ticketPK");
            bus.post(new FullTicketRequestEvent("Token " + UserDataCache.get().getAuthtoken(), ticketPK));
            Log.i("CustomLog", "Sent full ticket request event");
            return true;
        } else
        {
            Log.e("CustomLog", "Problem while requesting full ticket details for ticket pk: " + ticketPK);
            return false;
        }
    }

    private void setupFragments()
    {
        if(tabNames == null)
            tabNames = new ArrayList<>();
        tabNames.add(getString(R.string.ticketDetail_tabNameDetail));
//        tabNames.add(getString(R.string.ticketDetail_tabNameComments));
//        tabNames.add(getString(R.string.ticketDetail_tabNameHistory));
    }

    private void setupPager()
    {
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.ticketDetail_viewpager);
        actionBar = getSupportActionBar();
        pagerAdapter = new TicketDetailPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabNames) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
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
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onTicketDetailInteraction(Uri uri)
    {

    }
}
