package org.ec.androidticket.activities.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyActionBarActivity;
import org.ec.androidticket.activities.home.TicketHomeActionBarActivity;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginFailureEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginSuccessEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoggedOutEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LoginEvent;
import org.ec.androidticket.backend.Async.events.loginEvents.LogoutEvent;
import org.ec.androidticket.backend.managers.CookieManager;
import org.ec.androidticket.backend.models.internal.CredentialCookie;
import org.ec.androidticket.backend.models.internal.UserDataCache;

public class TicketLoginActionBarActivity extends MyActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_login);

        setupListeners();
        setupCookie();
    }

    private void setupCookie()
    {
        CredentialCookie cookie = CookieManager.readCookie(getApplicationContext());
        if (cookie != null) // Si le cookie a été correctement lu...
        {
            EditText emailField = (EditText) findViewById(R.id.email_field);
            EditText passwordField = (EditText) findViewById(R.id.password_field);
            emailField.setText(cookie.getEmail());
            passwordField.setText(cookie.getPassword());
        }
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

    public void onLoggedOut(LoggedOutEvent event)
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
        // Met les informations utilisateur en cache
        UserDataCache.get().applyLoginSuccessEvent(event);

        Context context = getApplicationContext();
        if(UserDataCache.get().isActive())
        {
            Intent toHome = new Intent(TicketLoginActionBarActivity.this, TicketHomeActionBarActivity.class);
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
}
