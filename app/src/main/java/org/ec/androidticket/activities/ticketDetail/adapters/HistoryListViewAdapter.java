package org.ec.androidticket.activities.ticketDetail.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.models.ticketing.variants.HistoryDiet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryListViewAdapter extends BaseAdapter
{
    private Context context;
    private List<HistoryDiet> sourceList;

    public HistoryListViewAdapter(Context context, List<HistoryDiet> sourceList)
    {
        this.context = context;
        if(sourceList != null)
            this.sourceList = sourceList;
        else
            this.sourceList = new ArrayList<>();
    }

    public void updateSourceList(List<HistoryDiet> newSourceList)
    {
        this.sourceList = newSourceList;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return sourceList.size();
    }

    @Override
    public HistoryDiet getItem(int position)
    {
        return sourceList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        HistoryDietHolder stockage;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.ticket_history_row, parent, false);

            stockage = new HistoryDietHolder();
            stockage.newStatus = (TextView) convertView.findViewById(R.id.ticketHistory_ticketStatus);
            stockage.updateDate = (TextView) convertView.findViewById(R.id.ticketHistory_dateUpdated);
            stockage.reason = (TextView) convertView.findViewById(R.id.ticketHistory_reason);

            convertView.setTag(stockage);
        } else
        {
            stockage = (HistoryDietHolder) convertView.getTag();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy h:m:s");

        stockage.newStatus.setText(sourceList.get(position).getNewStatusName());
        stockage.updateDate.setText(dateFormat.format(sourceList.get(position).getUpdateDate()));
        stockage.reason.setText(sourceList.get(position).getUpdateReason());

        return convertView;
    }
}
