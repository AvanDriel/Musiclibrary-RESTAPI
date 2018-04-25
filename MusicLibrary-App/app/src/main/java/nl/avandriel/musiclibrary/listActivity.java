package nl.avandriel.musiclibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class listActivity extends AppCompatActivity {
    //define variables
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    AlbumAdapter albumAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Define adapter for custom listview/rowlayout
        albumAdapter = new AlbumAdapter(this, R.layout.row_layout);
        listView = findViewById(R.id.listview);
        listView.setAdapter(albumAdapter);

        //Get data from intent
        json_string = getIntent().getExtras().getString("data");

        //For every album, pass its data to the albumadapter where it makes a new row
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("items");
            int count = 0;
            String title, artist, genre;

            while(count<jsonArray.length()){

                JSONObject JO = jsonArray.getJSONObject(count);
                title = JO.getString("title");
                artist = JO.getString("artist");
                genre = JO.getString("genre");

                Albums albums = new Albums(title, artist, genre);
                albumAdapter.add(albums);

                count++;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
