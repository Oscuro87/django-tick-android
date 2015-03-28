package org.ec.androidticket.activities.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.GlobalSettings;
import org.ec.androidticket.backend.Async.RESTClient;
import org.ec.androidticket.backend.Async.responses.RestLoginResponses;
import org.ec.androidticket.backend.managers.CookieManager;
import org.ec.androidticket.backend.models.CredentialCookie;
import org.ec.androidticket.backend.models.UserData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TicketLoginActivity extends Activity
{
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
                action_login();
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

    private void action_logout()
    {
        RESTClient.get().logoutTicket(new Callback<RestLoginResponses.Logout>()
        {
            @Override
            public void success(RestLoginResponses.Logout logout, Response response)
            {
                if (logout.isDisconnected())
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_logged_out_text), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_not_logged_in_text), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.e("CustomLog", "Logout unsuccessful: " + error.toString());
                Toast.makeText(getApplicationContext(), getString(R.string.toast_logout_error_text), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void action_login()
    {
        EditText emailField = (EditText) findViewById(R.id.email_field);
        EditText passwordField = (EditText) findViewById(R.id.password_field);
        String email = emailField.getText().toString();
        String pass = passwordField.getText().toString();

        RESTClient.get().authTicket(email, pass, new Callback<RestLoginResponses.Auth>()
        {
            @Override
            public void success(RestLoginResponses.Auth auth, Response response)
            {
                Context context = getApplicationContext();
                if (auth.isSuccess())
                {
                    Toast.makeText(context, R.string.toast_login_success_text, Toast.LENGTH_SHORT).show();
                    UserData.get().setLoggedIn(auth.isSuccess());
                    UserData.get().setFirstName(auth.getFirst_name());
                    UserData.get().setLastName(auth.getLast_name());
                    UserData.get().setEmail(auth.getEmail());
                    UserData.get().setStaff(auth.isStaff());
                }
                else
                {
                    Toast.makeText(context, R.string.toast_login_fail_text + ":\n" + auth.getReason(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.e("CustomLog", "Failed to action_login:\n" + error.toString());
                Toast.makeText(getApplicationContext(), "Login failure:\n" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
