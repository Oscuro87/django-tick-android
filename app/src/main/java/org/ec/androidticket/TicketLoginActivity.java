package org.ec.androidticket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ec.androidticket.backend.RestClient.RESTClient;
import org.ec.androidticket.backend.RestClient.RestResponses;
import org.ec.androidticket.backend.models.UserData;

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

        // TODO: Cookie pour ne pas devoir réécrire ses creds à chaque fois.
        String[] cookie = readCookie();
    }

    private void setupListeners()
    {
        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText emailField = (EditText)findViewById(R.id.email_field);
                String email = emailField.getText().toString();
                EditText passwordField = (EditText)findViewById(R.id.password_field);
                String pass = passwordField.getText().toString();
                action_login(email, pass);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Écrit un cookie permettant de ne pas devoir rentrer son login à chaque fois.
     * Ne fonctionne que si l'option "remember me" est active!
     */
    private void writeCookie()
    {

    }

    /**
     * Retourne les informations de cookie sous la forme ["email", "mot de passe"].
     * @return Un array sous la forme ["email", "mot de passe"].
     */
    private String[] readCookie()
    {
        String[] cookie = new String[2];

        return cookie;
    }

    private void action_logout()
    {
        RESTClient.get().logoutTicket(new Callback<RestResponses.Logout>()
        {
            @Override
            public void success(RestResponses.Logout logout, Response response)
            {
                if(logout.isDisconnected())
                    Log.i("CustomLog", "Succesfully logged out!");
                else
                    Log.i("CustomLog", "You are not logged in.");
            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.i("CustomLog", "Logout unsuccessful: " + error.toString());
            }
        });
    }

    private void action_login(String email, String pass)
    {
        RESTClient.get().authTicket(email, pass, new Callback<RestResponses.Auth>()
        {
            @Override
            public void success(RestResponses.Auth auth, Response response)
            {
                UserData userData = auth.getUserDataObject();
                Context context = getApplicationContext();
                Toast.makeText(context, R.string.toast_login_success_text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.e("CustomLog", "Failed to action_login:\n" + error.toString());
                Toast.makeText(getApplicationContext(), "\"Failed to action_login:\\n\" + error.toString()", Toast.LENGTH_LONG).show();
            }
        });
    }
}
