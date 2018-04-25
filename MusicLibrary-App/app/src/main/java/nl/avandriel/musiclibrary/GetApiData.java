package nl.avandriel.musiclibrary;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetApiData extends AsyncTask<String, String, String>  {
    Context context;

    GetApiData(Context context){
        this.context = context;
    }

    //Function that does get request to server in background
    @Override
    protected String doInBackground(String... params) {

        String result = null;

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            //Get url from first parameter
            URL url = new URL(params[0]);

            //connect to the network
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();

            //get the data
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer data = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null){
                data.append(line);
            }

            //Save data into result var
            String json = data.toString();
            result = json;

            //And give the result back
            return result;

        } catch (IOException e) {
            //catch the errors here if the internet crashed or is not working
            e.printStackTrace();
            Log.d("ERROR" , String.valueOf(e));
            return null;

        } finally {
            //close the connection
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    //after the get, pass the data in an intent to the listactivity
    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, listActivity.class);
        intent.putExtra("data", result);
        context.startActivity(intent);
    }
}
