package org.ec.androidticket.activities.ticketDetail.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.ec.androidticket.activities.ticketDetail.fragments.TicketDetailFragment;

/**
 * Classe décrivant le comportement des "tabs" du viewpager.
 */
public class TicketDetailPagerAdapter extends FragmentPagerAdapter
{
    // Le nombre de fragments pré-construits, 3 dans ce cas-ci. (vue ticket, historique et commentaires)
    private final int FRAGMENTS_COUNT = 3;

    public TicketDetailPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            // TODO: getItem de l'adapter pager pour la vue detail ticket
            default:
                return new TicketDetailFragment();
        }
    }

    @Override
    public int getCount()
    {
        return FRAGMENTS_COUNT;
    }
}
