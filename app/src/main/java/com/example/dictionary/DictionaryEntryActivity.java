package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class DictionaryEntryActivity extends AppCompatActivity {
    TextView resultTextView;
    TextView wordTextView;
    TextView partOfSpeechTextView;
    TextView phoneticTextView;
    ImageView soundImageView;

    Word dictionaryWord;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_entry);

        resultTextView = findViewById(R.id.resultTextView);
        wordTextView = findViewById(R.id.wordTextView);
        partOfSpeechTextView = findViewById(R.id.partOfSpeechTextView);
        phoneticTextView = findViewById(R.id.phoneticTextView);
        soundImageView = findViewById(R.id.soundImageView);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            try{
                String word = extras.getString("wordJSON");
                JSONArray array = new JSONArray(word);
                JSONObject jsonObject1 = array.getJSONObject(0);
                String wordFromDictionary = jsonObject1.optString("word");
                String phonetic = jsonObject1.optString("phonetic");
                String audioURL = jsonObject1.getJSONArray("phonetics").getJSONObject(0).optString("audio");
                String partOfSpeech = jsonObject1.getJSONArray("meanings").getJSONObject(0).optString("partOfSpeech");
                String definition = jsonObject1.getJSONArray("meanings").getJSONObject(0).getJSONArray("definitions").getJSONObject(0).optString("definition");

                dictionaryWord = new Word(wordFromDictionary, phonetic, audioURL,partOfSpeech, definition);
                wordTextView.setText(dictionaryWord.word);
                partOfSpeechTextView.setText(dictionaryWord.partOfSpeech);
                resultTextView.setText(dictionaryWord.definition);
                phoneticTextView.setText(dictionaryWord.phonetic);

            } catch(JSONException e) {
                e.printStackTrace();
            }

        }

        soundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });
    }

    public void playAudio(){
        String url = dictionaryWord.phoneticsURL;

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}