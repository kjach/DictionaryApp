package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchWordActivity extends AppCompatActivity {
    Button searchWordBtn;
    EditText searchWordText;
    String query;

    public class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String json = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();

                while(data != -1) {
                    char letter = (char) data;
                    json += letter;
                    data = inputStreamReader.read();
                }
                return json;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("json", s);
            try {
//                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = new JSONArray(s);
//                String dictionaryWord = jsonObject.toString();
//                JSONArray jsonArray = jsonObject.getJSONArray("0");
                Intent intent = new Intent(SearchWordActivity.this, DictionaryEntryActivity.class);
                intent.putExtra("wordJSON", jsonArray.toString());
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchWordText = findViewById(R.id.searchWordText);
        searchWordBtn = findViewById(R.id.searchWordBtn);
        searchWordBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.RoyalBlue)));

        searchWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = searchWordText.getText().toString().replaceAll(" ", "%20");
                Log.i("query", query);
                DownloadTask task = new DownloadTask();
                task.execute("https://api.dictionaryapi.dev/api/v2/entries/en/" + query);
            }
        });


    }

//    public Void onClick() {
//        DownloadTask task = new DownloadTask();
//        task.execute("https://api.dictionaryapi.dev/api/v2/entries/en/" + query);
//    }

//    public void showDictionaryEntry(View view) {
//        Intent intent = new Intent(this, DictionaryEntryActivity.class);
//        intent.putExtra("wordJSON", json);
//        startActivity(intent);
//
//    }
}