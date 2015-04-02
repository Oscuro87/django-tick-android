package org.ec.androidticket.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyActivity;
import org.ec.androidticket.activities.home.adapters.SimpleTicketListViewAdapter;
import org.ec.androidticket.activities.login.TicketLoginActivity;
import org.ec.androidticket.activities.ticketDetail.TicketDetailActivity;
import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.events.loginEvents.LoggedOutEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LogoutEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.SimpleTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.SimpleTicketRequestResponseEvent;
import org.ec.androidticket.backend.Async.services.AuthService;
import org.ec.androidticket.backend.Async.services.TicketService;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.internal.UserSimpleTicketCache;
import org.ec.androidticket.backend.models.ticketing.Ticket;

import java.util.List;

public class TicketHomeActivity extends MyActivity implements SearchView.OnQueryTextListener
{
    private SearchView searchView;
    private ListView listView;


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

        String authtoken = UserDataCache.get().getAuthtoken();
        bus.post(new SimpleTicketRequestEvent(authtoken));
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

        switch (item.getItemId())
        {
            case R.id.action_logout:
                bus.post(new LogoutEvent(UserDataCache.get().getAuthtoken()));
                return true;
            case R.id.action_createBuilding:
                // TODO: bouton create building
                return true;
            case R.id.action_createTicket:
                // TODO: bouton create ticket
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
    public void onSimpleTicketRequestResponse(SimpleTicketRequestResponseEvent event)
    {
        List<Ticket> ticketList = event.getTickets();

        UserSimpleTicketCache cache = UserSimpleTicketCache.get();

        cache.purge();
        for (Ticket t : ticketList)
            cache.putTicketInCache(t);

        setupListView();
        setupSearchView();
    }

    private void goToTicketDetailView(Integer ticketPK)
    {
        Bundle arguments = new Bundle(1);
        arguments.putInt("ticketPK", ticketPK);
        Intent intent = new Intent(TicketHomeActivity.this, TicketDetailActivity.class);
        intent.putExtras(arguments);
        startActivity(intent);
    }

    private void setupListView()
    {
        UserSimpleTicketCache cache = UserSimpleTicketCache.get();
        SimpleTicketListViewAdapter adapter = new SimpleTicketListViewAdapter(getApplicationContext(), cache.getCache());
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Ticket selected = (Ticket) listView.getItemAtPosition(position);
                goToTicketDetailView(selected.getPk());
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
            listView.setFilterText(newText);
        return true;
    }
}
