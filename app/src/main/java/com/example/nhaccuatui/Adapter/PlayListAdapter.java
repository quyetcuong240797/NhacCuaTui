package com.example.nhaccuatui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhaccuatui.Model.Playlist;
import com.example.nhaccuatui.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<Playlist> {


    public PlayListAdapter(Context context, int resource, List<Playlist> objects) {
        super(context, resource, objects);
    }


    class ViewHolder {
        TextView tvtenplaylist;
        ImageView imgbackgroundplaylist,imgplaylist;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView =layoutInflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.tvtenplaylist =convertView.findViewById(R.id.tvtenplaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewsongplaylist);
            viewHolder.imgbackgroundplaylist = convertView.findViewById(R.id.imageviewbackgroudplaylist);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist =getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlaylist()).into(viewHolder.imgbackgroundplaylist);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgplaylist);
        viewHolder.tvtenplaylist.setText(playlist.getTen());
        return convertView;
    }
}
