package org.ec.androidticket.activities.ticketDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyActionBarActivity;
import org.ec.androidticket.activities.ticketDetail.adapters.TicketDetailPagerAdapter;
import org.ec.androidticket.activities.ticketDetail.fragments.TicketCommentFragment;
import org.ec.androidticket.activities.ticketDetail.fragments.TicketDetailFragment;
import org.ec.androidticket.activities.ticketDetail.fragments.TicketHistoryFragment;
import org.ec.androidticket.backend.Async.events.ticketEvents.CommentRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.CommentRequestFailureEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.CommentRequestSuccessEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.FullTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.FullTicketRequestFailureEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.FullTicketRequestSuccessEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.HistoryRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.HistoryRequestFailureEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.HistoryRequestSuccessEvent;
import org.ec.androidticket.backend.models.internal.FullTicketCache;
import org.ec.androidticket.backend.models.internal.UserDataCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Vue détaillée d'un ticket, divisée en 3 parties (fragments):
 * La vue ticket contenant toutes ses informations
 * La vue commentaires qui permet de consulter ou écrire un nouveau commentaire
 * La vue historique ticket
 * Tutorial pour viewpager : http://www.androidhive.info/2013/10/android-tab-layout-with-swipeable-views-1/
 */

public class TicketDetailActivity extends MyActionBarActivity implements android.support.v7.app.ActionBar.TabListener
{
    private String ticketCode;
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

        setupFragments();
        setupPager();

        if (!unpackParameters())
        {
            Toast.makeText(getApplicationContext(), getString(R.string.ticketDetail_paramUnpackError), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean unpackParameters()
    {
        Intent thisIntent = getIntent();
        Bundle params = thisIntent.getExtras();

        if (params != null)
        {
            this.ticketCode = params.getString("ticketCode");
            requestTicketInfo(ticketCode);
            return true;
        } else
        {
            Log.e("CustomLog", "Problem while requesting full ticket details for ticket code: " + ticketCode);
            return false;
        }
    }

    private void requestTicketInfo(String ticketCode)
    {
        FullTicketCache cache = FullTicketCache.get();
        bus.post(new FullTicketRequestEvent("Token " + UserDataCache.get().getAuthtoken(), ticketCode));
        bus.post(new CommentRequestEvent("Token " + UserDataCache.get().getAuthtoken(), ticketCode));
        bus.post(new HistoryRequestEvent("Token " + UserDataCache.get().getAuthtoken(), ticketCode));
    }

    private void setupFragments()
    {
        if (tabNames == null)
            tabNames = new ArrayList<>();
        tabNames.add(getString(R.string.ticketDetail_tabNameDetail));
        tabNames.add(getString(R.string.ticketDetail_tabNameComments));
        tabNames.add(getString(R.string.ticketDetail_tabNameHistory));
    }

    private void setupPager()
    {
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.ticketDetail_viewpager);
        actionBar = getSupportActionBar();
        if (pagerAdapter == null)
            pagerAdapter = new TicketDetailPagerAdapter(getFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabNames)
        {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageSelected(int position)
            {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if(viewPager.getCurrentItem() != 0)
            viewPager.setCurrentItem(0, true);
        else
            finish();
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

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction)
    {
        viewPager.setCurrentItem(tab.getPosition());
    }

    public void refreshFragments()
    {
        TicketDetailFragment tdf = (TicketDetailFragment) pagerAdapter.getRegisteredFragment(0);
        TicketCommentFragment tcf = (TicketCommentFragment) pagerAdapter.getRegisteredFragment(1);
        TicketHistoryFragment thf = (TicketHistoryFragment) pagerAdapter.getRegisteredFragment(2);

        if(tdf != null)
            tdf.onRefreshRequested();
        if(tcf != null)
            tcf.onRefreshRequested();
        if(thf != null)
            thf.onRefreshRequested();
    }

    @Subscribe
    public void onTicketInformationsReceived(FullTicketRequestSuccessEvent event)
    {
        // Mettre en cache
        FullTicketCache.get().setTicketCache(event.getTicket());
        refreshFragments();
        Log.i("CustomLog", "Received full ticket details loaded event");
    }

    @Subscribe
    public void onTicketInformationsNotReceived(FullTicketRequestFailureEvent event)
    {
        Toast.makeText(getApplicationContext(), getString(R.string.TicketDetail_informationsFail), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Subscribe
    public void onTicketCommentsReceived(CommentRequestSuccessEvent event)
    {
        FullTicketCache.get().setCommentCache(event.getComments());
        refreshFragments();
    }

    @Subscribe
    public void onTicketCommentsNotReceived(CommentRequestFailureEvent event)
    {
        Toast.makeText(getApplicationContext(), getString(R.string.TicketDetail_commentsFail), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Subscribe
    public void onTicketCommentsReceived(HistoryRequestSuccessEvent event)
    {
        FullTicketCache.get().setHistoryCache(event.getHistory());
        refreshFragments();
    }

    @Subscribe
    public void onTicketCommentsNotReceived(HistoryRequestFailureEvent event)
    {
        Toast.makeText(getApplicationContext(), getString(R.string.TicketDetail_historyFail), Toast.LENGTH_SHORT).show();
        finish();
    }
}
