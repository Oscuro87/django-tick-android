package org.ec.androidticket.activities.createTicket;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.ec.androidticket.R;

/**
 * A faire :
 * - Event demande des catégories
 * - Event demande des sous-catégories (séparé car c'est demandé uniquement après que la catégorie soit sélectionnée)
 * - Event demande des bâtiments disponibles pour l'utilisateur actuel
 * - Diviser la création de tickets en plusieurs parties plutôt que de faire tout sur une seule vue
 * - Ajouter la possibilité d'attacher une photo au ticket (+ server side!)
 *
 *
 * Champs requis:
 * - Catégorie
 */

public class CreateTicketActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_ticket, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
