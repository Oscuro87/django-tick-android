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
import org.ec.androidticket.backend.models.CredentialCookie;

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

        CredentialCookie cookie = readCookie();
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
                // Créer un cookie
                writeCookie();

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
        CheckBox rememberMe = (CheckBox) findViewById(R.id.rememberMe);
        EditText emailField = (EditText) findViewById(R.id.email_field);
        EditText passwordField = (EditText) findViewById(R.id.password_field);

        if (rememberMe.isChecked())
        {
            try
            {
                File cookie = new File(getApplicationContext().getCacheDir(), GlobalSettings.CREDENTIAL_COOKIE_FILENAME);
                if (!cookie.exists())
                    cookie.createNewFile();
                FileWriter writer = new FileWriter(cookie);
                writer.append(emailField.getText().toString());
                writer.append(" ");
                // TODO: MD5 le mdp?
                writer.append(passwordField.getText().toString());
                writer.flush();
                writer.close();
            } catch (IOException e)
            {
                Log.e("CustomLog", e.getMessage());
            }

        } else
        {
            // On détruit le cookie car l'utilisateur ne veut plus être 'enregistré'
            destroyCookie();
        }
    }

    /**
     * Détruit un cookie qui n'est plus nécéssaire.
     */
    private void destroyCookie()
    {
        File cookie = new File(getApplicationContext().getCacheDir(),
                GlobalSettings.CREDENTIAL_COOKIE_FILENAME);
        if (cookie.exists())
            cookie.delete();
    }

    /**
     * Retourne les informations de cookie.
     * @return Un array sous la forme ["email", "mot de passe"].
     */
    private CredentialCookie readCookie()
    {
        File cookieFile = new File(getApplicationContext().getCacheDir(), GlobalSettings.CREDENTIAL_COOKIE_FILENAME);
        if(!cookieFile.exists())
            return null;
        else
        {
            try
            {
                String email;
                String password;
                InputStream in = new FileInputStream(cookieFile);
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader breader = new BufferedReader(reader);

                String line = breader.readLine();
                breader.close();
                email = line.split(" ")[0];
                password = line.split(" ")[1];

                return new CredentialCookie(email, password);
            } catch (FileNotFoundException e)
            {
                Log.e("CustomLog", e.getMessage());
                return null;
            } catch (IOException e)
            {
                Log.e("CustomLog", e.getMessage());
                return null;
            }
        }
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
                Toast.makeText(context, R.string.toast_login_success_text, Toast.LENGTH_SHORT).show();
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
