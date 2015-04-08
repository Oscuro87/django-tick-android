package org.ec.androidticket.activities.createBuilding;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.events.buildingEvents.BuildingCreationEvent;
import org.ec.androidticket.backend.Async.events.buildingEvents.BuildingCreationResponseEvent;
import org.ec.androidticket.backend.models.internal.UserDataCache;

import java.util.Locale;

public class CreateBuildingActivity extends ActionBarActivity
{
    private Button createBuildingButton;
    private BuildingHolder storage = new BuildingHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_building);

        BusDepot.get().getBus(BusDepot.BusType.GENERAL).register(this);
        createBuildingButton = (Button) findViewById(R.id.createBuildingButton);

        setupListeners();
    }

    private void setupListeners()
    {
        createBuildingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO: fix probleme conversion des noms de pays
                storage.country = "BE";
                storage.address = ((EditText) findViewById(R.id.createBuildingAddressEdit)).getText().toString();
                storage.postcode = ((EditText) findViewById(R.id.createBuildingPostcodeEdit)).getText().toString();
                storage.vicinity = ((EditText) findViewById(R.id.createBuildingVicinityEdit)).getText().toString();
                storage.buildingName = ((EditText) findViewById(R.id.createBuildingNameEdit)).getText().toString();

                if(storage.country.equals(""))
                {
                    Toast.makeText(getApplicationContext(), getString(R.string.createBuildingWrongCountryInput), Toast.LENGTH_SHORT).show();
                    return;
                }

                BusDepot.get().getBus(BusDepot.BusType.GENERAL).post(new BuildingCreationEvent(UserDataCache.get().getAuthtoken(), storage.buildBuilding()));
            }
        });
    }

    @Subscribe
    public void onBuildingCreationResponseEvent(BuildingCreationResponseEvent event)
    {
        if (event.success)
            Toast.makeText(getApplicationContext(), getString(R.string.buildingCreationSuccess), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), getString(R.string.buildingCreationFailure) + "\n" + event.reason, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_building, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
