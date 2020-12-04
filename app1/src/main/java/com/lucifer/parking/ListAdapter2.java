package com.lucifer.parking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter2 extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Date;
    ArrayList<String> Time;
    ArrayList<String> Duration;
    ArrayList<String> Username;
    ArrayList<String> Type;


    public ListAdapter2(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> date,
            ArrayList<String> time,
            ArrayList<String> duration,
            ArrayList<String> username,
            ArrayList<String> type
    )
    {

        this.context = context2;
        this.ID = id;
        this.Date = date;
        this.Time = time;
        this.Duration = duration;
        this.Username = username;
        this.Type = type;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items2, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewID);
            holder.Date_TextView = (TextView) child.findViewById(R.id.textViewDATE);
            holder.TimeTextView = (TextView) child.findViewById(R.id.textViewTIME);
            holder.DurationTextView = (TextView) child.findViewById(R.id.textViewDURATION);
            holder.UsernameTextView = (TextView) child.findViewById(R.id.textViewUNAME);
            holder.TypeTextView = (TextView) child.findViewById(R.id.textViewTYPE);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.Date_TextView.setText(Date.get(position));
        holder.TimeTextView.setText(Time.get(position));
        holder.DurationTextView.setText(Duration.get(position));
        holder.UsernameTextView.setText(Username.get(position));
        holder.TypeTextView.setText(Type.get(position));

        return child;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Date_TextView;
        TextView TimeTextView;
        TextView DurationTextView;
        TextView UsernameTextView;
        TextView TypeTextView;
    }

}