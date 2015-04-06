package org.ec.androidticket.activities.ticketDetail.adapters;

import android.app.Fragment;
import android.app.FragmentManager;

import org.ec.androidticket.activities.SmartFragmentStatePagerAdapter;
import org.ec.androidticket.activities.ticketDetail.fragments.TicketCommentFragment;
import org.ec.androidticket.activities.ticketDetail.fragments.TicketDetailFragment;
import org.ec.androidticket.activities.ticketDetail.fragments.TicketHistoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe décrivant le comportement des "tabs" du viewpager.
 */
public class TicketDetailPagerAdapter extends SmartFragmentStatePagerAdapter
{
    // Le nombre de fragments pré-construits, 3 dans ce cas-ci. (vue ticket, historique et commentaires)
    private final int FRAGMENTS_COUNT = 3;
    private final List<Fragment> frags;

    public TicketDetailPagerAdapter(FragmentManager fm)
    {
        super(fm);
        frags = new ArrayList<>(FRAGMENTS_COUNT);
        frags.add(0, TicketDetailFragment.newInstance());
        frags.add(1, TicketCommentFragment.newInstance());
        frags.add(2, TicketHistoryFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position)
    {
        return frags.get(position);
    }

    @Override
    public int getCount()
    {
        return FRAGMENTS_COUNT;
    }
}
