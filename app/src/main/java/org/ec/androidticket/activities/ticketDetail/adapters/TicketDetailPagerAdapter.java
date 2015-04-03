package org.ec.androidticket.activities.ticketDetail.adapters;

import android.app.Fragment;
import android.app.FragmentManager;

import org.ec.androidticket.activities.custom.SmartFragmentStatePagerAdapter;
import org.ec.androidticket.activities.ticketDetail.fragments.TicketCommentFragment;
import org.ec.androidticket.activities.ticketDetail.fragments.TicketDetailFragment;

/**
 * Classe décrivant le comportement des "tabs" du viewpager.
 */
public class TicketDetailPagerAdapter extends SmartFragmentStatePagerAdapter
{
    // Le nombre de fragments pré-construits, 3 dans ce cas-ci. (vue ticket, historique et commentaires)
    private final int FRAGMENTS_COUNT = 2;

    public TicketDetailPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return TicketDetailFragment.newInstance();
            case 1:
                return TicketCommentFragment.newInstance();
            // TODO: 2 autres fragments
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return FRAGMENTS_COUNT;
    }
}
