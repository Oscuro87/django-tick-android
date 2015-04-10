package org.ec.androidticket.activities.createTicket.adapters;

import android.app.Fragment;
import android.app.FragmentManager;

import org.ec.androidticket.activities.SmartFragmentStatePagerAdapter;
import org.ec.androidticket.activities.createTicket.fragments.CreateTicketBuildingFragment;
import org.ec.androidticket.activities.createTicket.fragments.CreateTicketCategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class TicketCreationPagerAdapter extends SmartFragmentStatePagerAdapter
{
    private final int FRAGMENTS_COUNT;
    private final List<Fragment> fragments;

    public TicketCreationPagerAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
        fragments = new ArrayList<>();
        fragments.add(new CreateTicketCategoryFragment());
        fragments.add(new CreateTicketBuildingFragment());
        FRAGMENTS_COUNT = fragments.size();
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return FRAGMENTS_COUNT;
    }
}
