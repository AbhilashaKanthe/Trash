package com.example.abhilasha.marvelsfacts.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhilasha.marvelsfacts.R;
import com.example.abhilasha.marvelsfacts.models.SuperHero;
import com.squareup.picasso.Picasso;


public class ExhibitsAdapter extends ArrayAdapter<SuperHero> {
    public ExhibitsAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if( convertView == null ) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from( getContext() ).inflate( R.layout.view_exhibit_list_item, parent, false );

            holder.name = (TextView) convertView.findViewById( R.id.name );
            holder.thumbnail = (ImageView) convertView.findViewById( R.id.thumbnail );

            convertView.setTag( holder );
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText( getItem(position).getName() );


        Picasso.with(getContext()).load( getItem( position ).getThumbnail() ).into( holder.thumbnail );

        return convertView;
    }

    private class  ViewHolder {
        ImageView thumbnail;
        TextView name;
    }

}
