package nl.avandriel.musiclibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {;
    //define api url to get the Album data
    public final static String APIUrl = "http://188.226.174.141:8000/api/albums";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    protected void onStart() {
        super.onStart();

        //Get Custom name setting and add it into the title
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String title = sharedPref.getString("nameSetting", "");
        TextView t = findViewById(R.id.mainTitle);
        t.setText("Welcome, " + title + "!");
    }

    //See your albums Button
    public void buttonClick(View v){
        //Execute the asynctask with the before declared api url
        new GetApiData(MainActivity.this).execute(APIUrl);
    }

    //Settings Button
    public void settingsClick(View v){
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        MainActivity.this.startActivity(intent);
    }

    //Add album button
    public void addAlbumClick(View v){
        Intent intent = new Intent(MainActivity.this, NewAlbumActivity.class);
        MainActivity.this.startActivity(intent);
    }
}
