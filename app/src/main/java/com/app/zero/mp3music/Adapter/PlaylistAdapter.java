package com.app.zero.mp3music.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.zero.mp3music.Model.Playlist;
import com.app.zero.mp3music.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder {
        TextView txtTen;
        ImageView imgBackground, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTen = convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgBackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        assert playlist != null;
        Picasso.get().load(playlist.getHinhPlaylist())
                .into(viewHolder.imgBackground);
        Picasso.get().load(playlist.getIcon())
                .into(viewHolder.imgPlaylist);

        viewHolder.txtTen.setText(playlist.getTen());
        return convertView;
    }
}
