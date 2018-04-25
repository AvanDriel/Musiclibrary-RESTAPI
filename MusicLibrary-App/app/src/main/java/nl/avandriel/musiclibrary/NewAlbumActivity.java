package nl.avandriel.musiclibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class NewAlbumActivity extends AppCompatActivity {
    String artist, title, genre;
    EditText artistEdit, titleEdit, genreEdit;

    //Declare a private  RequestQueue variable
    private RequestQueue requestQueue;
    private static NewAlbumActivity mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance=this;
        setContentView(R.layout.activity_new_album);

        //find the inputfields where we need to get the data from
        artistEdit = findViewById(R.id.artistEditText);
        titleEdit = findViewById(R.id.titleEditText);
        genreEdit = findViewById(R.id.genreEditText);
    }

    public static synchronized NewAlbumActivity getInstance()
    {
        return mInstance;
    }

    //Add album button
    public void addClick(View v) throws JSONException {

        //Get content of input fields
        artist = artistEdit.getText().toString();
        title = titleEdit.getText().toString();
        genre = genreEdit.getText().toString();

        //URL of the request we are sending
        String url = "http://188.226.174.141:8000/api/albums";

        //Make the JSON object we're going to post to the server
        JSONObject postparams=new JSONObject();
        postparams.put("artist", artist);
        postparams.put("genre", genre);
        postparams.put("title", title);
        postparams.put("listened", "false");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //Success Callback

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Failure Callback

                    }
                });


        // Adding the request to the queue along with a unique string tag
        NewAlbumActivity.getInstance().addToRequestQueue(jsonObjReq,"postRequest");


        //after the post is done, go to the main screen
        Intent intent = new Intent(NewAlbumActivity.this, MainActivity.class);
        NewAlbumActivity.this.startActivity(intent);
    }


    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null)
            requestQueue= Volley.newRequestQueue(getApplicationContext());

        return requestQueue;
    }

    public void addToRequestQueue(Request request, String tag)
    {
        request.setTag(tag);
        getRequestQueue().add(request);

    }

    public void cancelAllRequests(String tag)
    {
        getRequestQueue().cancelAll(tag);
    }
}
