package org.ec.androidticket.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyActionBarActivity;
import org.ec.androidticket.activities.createBuilding.CreateBuildingActivity;
import org.ec.androidticket.activities.createTicket.CreateTicketActivity;
import org.ec.androidticket.activities.home.adapters.SimpleTicketListViewAdapter;
import org.ec.androidticket.activities.ticketDetail.TicketDetailActivity;
import org.ec.androidticket.backend.Async.events.loginEvents.LoggedOutEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LogoutEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.SimpleTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.SimpleTicketResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketDetailResponseEvent;
import org.ec.androidticket.backend.models.internal.SimpleTicketCache;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.ticketing.Ticket;

import java.util.List;

public class TicketHomeActivity extends MyActionBarActivity implements SearchView.OnQueryTextListener
{
    private SearchView searchView;
    private ListView listView;
    private Filter searchFilter;
    private SimpleTicketListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_home);

        Toast.makeText(getApplicationContext(), R.string.toast_more_options, Toast.LENGTH_SHORT).show();

        TextView header = (TextView) (findViewById(R.id.ticketHomeHeader));
        header.setText(header.getText() + " " + UserDataCache.get().getFullName());

        setupViewCache();
        setupListeners();

        refreshTickets(true);
    }

    private void setupViewCache()
    {
        searchView = (SearchView)findViewById(R.id.simpleTicketSearchView);
        listView = (ListView)findViewById(R.id.listView);
    }

    private void setupListeners()
    {
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        refreshTickets(true);
    }

    // TODO: Ajouter un bouton qui force à rafraichir la liste des tickets de l'utilisateur
    private void refreshTickets(boolean forceRefresh)
    {
        String authtoken = UserDataCache.get().getAuthtoken();

        if(SimpleTicketCache.get().getCache().size() <= 0 || forceRefresh)
            bus.post(new SimpleTicketRequestEvent(authtoken));

        refreshTicketView();
    }

    private void refreshTicketView()
    {
        setupListView();
        setupSearchView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_ticket_home, menu);

        if(UserDataCache.get().isStaff())
        {
            ((MenuItem)menu.findItem(R.id.action_show_own_tickets)).setVisible(true);
            ((MenuItem)menu.findItem(R.id.action_show_managed_tickets)).setVisible(true);
            ((MenuItem)menu.findItem(R.id.action_show_unmanaged_tickets)).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        Intent intent = null;

        switch (item.getItemId())
        {
            case R.id.action_logout:
                bus.post(new LogoutEvent(UserDataCache.get().getAuthtoken()));
                return true;
            case R.id.action_createBuilding:
                intent = new Intent(TicketHomeActivity.this, CreateBuildingActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_createTicket:
                intent = new Intent(TicketHomeActivity.this, CreateTicketActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_show_own_tickets:
                SimpleTicketCache.get().setBrowsingMode(SimpleTicketCache.BrowsingMode.OWN_TICKETS);
                refreshTicketView();
                return true;
            case R.id.action_show_managed_tickets:
                SimpleTicketCache.get().setBrowsingMode(SimpleTicketCache.BrowsingMode.MANAGED_TICKETS);
                refreshTicketView();
                return true;
            case R.id.action_show_unmanaged_tickets:
                SimpleTicketCache.get().setBrowsingMode(SimpleTicketCache.BrowsingMode.UNMANAGED_TICKETS);
                refreshTicketView();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onLoggedOut(LoggedOutEvent event)
    {
        Context context = getApplicationContext();
        if (event.disconnected)
        {
            Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show();
            goToLoginActivity();
        } else
            Toast.makeText(context, "Problem while logging out", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed()
    {
        bus.post(new LogoutEvent(UserDataCache.get().getAuthtoken()));
    }

    private void goToLoginActivity()
    {
        NavUtils.navigateUpFromSameTask(this);
        finish();
    }

    @Subscribe
    public void onSimpleTicketRequestResponse(SimpleTicketResponseEvent event)
    {
        if(!event.hasErrors())
        {
            List<Ticket> userTickets = event.getTickets();

            SimpleTicketCache cache = SimpleTicketCache.get();

            cache.purge();

            // mise en cache des tickets de l'utilisateur
            for (Ticket t : userTickets)
                cache.putUserTicket(t);

            // mise en cache des tickets gérés par l'utilsiateur
            for(Ticket t : event.getManagedByUser())
                cache.putManagedTicket(t);

            // mise en cache des ticktes non gérés par quiconque
            for(Ticket t : event.getUnmanagedTickets())
                cache.putUnmanagedTicket(t);

            refreshTicketView();
        }
        else
        {
            Toast.makeText(getApplicationContext(), getString(R.string.simpleTicket_failedTicketsLoad), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Subscribe
    public void onTicketUpdateRequestResponse(UpdateTicketDetailResponseEvent event)
    {
        refreshTickets(true);
    }

    private void goToTicketDetailView(String ticketCode)
    {
        Bundle arguments = new Bundle(1);
        arguments.putString("ticketCode", ticketCode);
        Intent intent = new Intent(TicketHomeActivity.this, TicketDetailActivity.class);
        intent.putExtras(arguments);
        startActivity(intent);
    }

    private void setupListView()
    {
        SimpleTicketCache cache = SimpleTicketCache.get();
        adapter = new SimpleTicketListViewAdapter(getApplicationContext(), cache.getCache());
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(false);
        searchFilter = adapter.getFilter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Ticket selected = (Ticket) listView.getItemAtPosition(position);
                goToTicketDetailView(selected.getTicketCode());
            }
        });
    }

    private void setupSearchView()
    {
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
    }


    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        if(newText.equals(""))
            listView.clearTextFilter();
        else
            searchFilter.filter(newText);
        return true;
    }
}
