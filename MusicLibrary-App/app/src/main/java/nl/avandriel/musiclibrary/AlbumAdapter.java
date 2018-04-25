package nl.avandriel.musiclibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends ArrayAdapter{
    List list = new ArrayList();
    public AlbumAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(Albums object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    //Make the album row with the data from the server
        View row;
        row = convertView;
        AlbumHolder albumHolder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            albumHolder = new AlbumHolder();
            albumHolder.tx_title = row.findViewById(R.id.tx_title);
            albumHolder.tx_artist = row.findViewById(R.id.tx_artist);
            albumHolder.tx_genre = row.findViewById(R.id.tx_genre);
            row.setTag(albumHolder);

        }else{
            albumHolder = (AlbumHolder)row.getTag();
        }

        Albums albums = (Albums)this.getItem(position);
        albumHolder.tx_title.setText(albums.getTitle());
        albumHolder.tx_artist.setText(albums.getArtist());
        albumHolder.tx_genre.setText(albums.getGenre());
        return row;
    }

    static class AlbumHolder {
        TextView tx_title, tx_artist, tx_genre;
    }
}
