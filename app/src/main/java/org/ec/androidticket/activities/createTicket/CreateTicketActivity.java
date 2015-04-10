package org.ec.androidticket.activities.createTicket;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyActionBarActivity;
import org.ec.androidticket.activities.createTicket.adapters.TicketCreationInformationHolder;
import org.ec.androidticket.activities.createTicket.adapters.TicketCreationPagerAdapter;
import org.ec.androidticket.activities.createTicket.fragments.CreateTicketFragmentInterface;
import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;

import java.util.ArrayList;
import java.util.List;

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
 *
 * Mode opératoire:
 * Chaque étape de la création du ticket est divisée en fragment:
 * - Fragment pour catégorie + sous catégorie
 * - Fragment pour le bâtiment + étage + bureau
 * - Fragment pour la description
 * - Fragment pour la photo à joindre (+ tard)
 */

public class CreateTicketActivity extends MyActionBarActivity implements ActionBar.TabListener, CreateTicketFragmentInterface
{
    private List<String> pageNames;
    private ViewPager viewPager;
    private TicketCreationPagerAdapter pagerAdapter;
    private ActionBar actionBar;
    public static TicketCreationInformationHolder creationInformation;


    public CreateTicketActivity()
    {
        super();
        creationInformation = new TicketCreationInformationHolder();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);

        setupFragments();
        setupPager();
    }

    private void setupFragments()
    {
        if(pageNames == null)
            pageNames = new ArrayList<>();
        pageNames.add(getString(R.string.ticketCreate_category));
        pageNames.add(getString(R.string.ticketCreate_building));
        pageNames.add(getString(R.string.ticketCreate_description));
//        pageNames.add(getString(R.string.ticketCreate_image));
        pageNames.add(getString(R.string.ticketCreate_confirmationPage));
    }

    private void setupPager()
    {
        viewPager = (ViewPager)findViewById(R.id.ticketCreation_viewpager);
        actionBar = getSupportActionBar();
        if(pagerAdapter == null)
            pagerAdapter = new TicketCreationPagerAdapter(getFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount());

        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for(String name : pageNames)
            actionBar.addTab(actionBar.newTab().setText(name).setTabListener(this));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_create_ticket, menu);
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
        super.onBackPressed();
        Toast.makeText(getApplicationContext(), getString(R.string.ticketCreate_cancelled), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onNextStepCalled()
    {
        int nextPage = viewPager.getCurrentItem() + 1;
        int maxPages = pagerAdapter.getCount();
        if(nextPage <= maxPages)
            viewPager.setCurrentItem(nextPage, true);
    }
}
