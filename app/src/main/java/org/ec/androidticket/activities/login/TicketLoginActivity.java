package org.ec.androidticket.activities.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.home.TicketHomeActivity;
import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginFailureEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginSuccessEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoggedOutEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LogoutEvent;
import org.ec.androidticket.backend.Async.services.TicketService;
import org.ec.androidticket.backend.managers.CookieManager;
import org.ec.androidticket.backend.models.internal.CredentialCookie;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.Async.services.AuthService;

public class TicketLoginActivity extends ActionBarActivity
{
    private Bus bus;
    private AuthService authService;
    private TicketService ticketService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_login);
        setupListeners();

        CredentialCookie cookie = CookieManager.readCookie(getApplicationContext());
        if (cookie != null) // Si le cookie a été correctement lu...
        {
            EditText emailField = (EditText) findViewById(R.id.email_field);
            EditText passwordField = (EditText) findViewById(R.id.password_field);
            emailField.setText(cookie.getEmail());
            passwordField.setText(cookie.getPassword());
        }

        bus = BusDepot.get().getBus(BusDepot.BusType.GENERAL);
        authService = AuthService.get();
        ticketService = TicketService.get();
    }

    private void setupListeners()
    {
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CheckBox rememberMe = (CheckBox) findViewById(R.id.rememberMe);
                EditText emailField = (EditText) findViewById(R.id.email_field);
                EditText passwordField = (EditText) findViewById(R.id.password_field);

                boolean remember = rememberMe.isChecked();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // Créer un cookie
                CookieManager.writeCookie(getApplicationContext(), email, password, remember);

                // Login
                bus.post(new LoginEvent(email, password));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // getMenuInflater().inflate(R.menu.menu_ticket_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        try
        {
            bus.register(this);
        } catch(IllegalArgumentException ignored)
        {
            Log.e("CustomLog", "Bus already registered.");
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    public void onBackPressed()
    {

        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onStop();

        if(UserDataCache.get().isLoggedIn())
            bus.post(new LogoutEvent(UserDataCache.get().getAuthtoken()));
    }

    public void loggedOut(LoggedOutEvent event)
    {
        if (event.disconnected)
        {
            Log.i("CustomLog", "Disconnection successful");
            Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.e("CustomLog", "Error while disconnecting!");
            Toast.makeText(getApplicationContext(), "Problem logging out!", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void onLoggedIn(LoginSuccessEvent event)
    {
        UserDataCache.get().setAuthtoken(event.authtoken);
        UserDataCache.get().setLoggedIn(true);
        UserDataCache.get().setStaff(event.is_staff);
        UserDataCache.get().setActive(event.is_active);
        UserDataCache.get().setEmail(event.email);
        UserDataCache.get().setFirstName(event.firstName);
        UserDataCache.get().setLastName(event.lastName);

        Context context = getApplicationContext();
        if(UserDataCache.get().isActive())
        {
            Intent toHome = new Intent(TicketLoginActivity.this, TicketHomeActivity.class);
            toHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            context.startActivity(toHome);
        }
        else
        {
            Toast.makeText(context, R.string.toast_banned_user, Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe
    public void onLoginFailure(LoginFailureEvent event)
    {
        Toast.makeText(getApplicationContext(), event.reason, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onLoggedOut(LoggedOutEvent event)
    {

    }
}
