package org.ec.androidticket.activities.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import org.ec.androidticket.activities.tickets.TicketHomeActivity;
import org.ec.androidticket.backend.busEvents.LoginFailureEvent;
import org.ec.androidticket.backend.busEvents.LoginSuccessEvent;
import org.ec.androidticket.backend.busEvents.LoggedOutEvent;
import org.ec.androidticket.backend.busEvents.LoginEvent;
import org.ec.androidticket.backend.busEvents.LogoutEvent;
import org.ec.androidticket.backend.managers.CookieManager;
import org.ec.androidticket.backend.models.internal.CredentialCookie;
import org.ec.androidticket.backend.models.internal.UserData;
import org.ec.androidticket.backend.services.AuthService;

public class TicketLoginActivity extends Activity
{
    private Bus eventBus;
    private AuthService authService;

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

        eventBus = new Bus();
        authService = new AuthService(eventBus, this);
    }

    private void setupListeners()
    {
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CheckBox rememberMe = (CheckBox)findViewById(R.id.rememberMe);
                EditText emailField = (EditText)findViewById(R.id.email_field);
                EditText passwordField = (EditText)findViewById(R.id.password_field);

                boolean remember = rememberMe.isChecked();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // Créer un cookie
                CookieManager.writeCookie(getApplicationContext(), email, password, remember);

                // Login
                eventBus.post(new LoginEvent(email, password));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ticket_login, menu);
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
        super.onStop();
        eventBus.post(new LogoutEvent(UserData.get().getSessionid()));
        eventBus.unregister(this);
    }

    public void loggedOut(LoggedOutEvent event)
    {
        if (event.disconnected)
        {
            Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Problem logging out!", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void onLoggedIn(LoginSuccessEvent event)
    {
        Context context = getApplicationContext();

        UserData.get().setSessionid(event.sessionID);
        UserData.get().setLoggedIn(true);
        UserData.get().setStaff(event.staff);
        UserData.get().setEmail(event.email);
        UserData.get().setFirstName(event.firstName);
        UserData.get().setLastName(event.lastName);
        Toast.makeText(context, R.string.toast_login_success_text, Toast.LENGTH_SHORT).show();

        Intent toHome = new Intent(this, TicketHomeActivity.class);
        toHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(toHome);
    }

    @Subscribe
    public void onLoggedOut(LoginFailureEvent event)
    {
        Toast.makeText(getApplicationContext(), event.reason, Toast.LENGTH_LONG).show();
    }
}
