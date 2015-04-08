package org.ec.androidticket.activities.ticketDetail.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.models.ticketing.variants.CommentDiet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentListViewAdapter extends BaseAdapter
{
    private Context context;
    private List<CommentDiet> comments;

    public CommentListViewAdapter(Context context, List<CommentDiet> baseComments)
    {
        if(baseComments != null)
            this.comments = baseComments;
        else
            this.comments = new ArrayList<>();
        this.context = context;
    }

    public void updateCommentsList(List<CommentDiet> newCommentsList)
    {
        this.comments = newCommentsList;
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
        return comments.size();
    }

    @Override
    public CommentDiet getItem(int position)
    {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CommentDietHolder storage;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.ticket_comment_row, parent, false);

            storage = new CommentDietHolder();
            storage.dateTV = (TextView) convertView.findViewById(R.id.ticketCommentRow_datetime);
            storage.commenterNameTV = (TextView) convertView.findViewById(R.id.ticketCommentRow_commenterName);
            storage.bodyTV = (TextView) convertView.findViewById(R.id.ticketCommentRow_commentBody);

            convertView.setTag(storage);
        }
        else
        {
            storage = (CommentDietHolder) convertView.getTag();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy h:m:s");

        storage.dateTV.setText(dateFormat.format(comments.get(position).getDateCreated()));
        storage.commenterNameTV.setText(comments.get(position).getCommenterName());
        storage.bodyTV.setText(comments.get(position).getCommentText());

        return convertView;
    }
}
