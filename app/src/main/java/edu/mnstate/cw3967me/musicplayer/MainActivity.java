package edu.mnstate.cw3967me.musicplayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    MediaPlayer mediaPlayer;//MediaPlayer

    RadioButton radio_refresh;
    RadioButton radio_shamisen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radio_refresh = (RadioButton) findViewById(R.id.radio_refresh);
        radio_shamisen = (RadioButton) findViewById(R.id.radio_shamisen);


        //set default song
        mediaPlayer = MediaPlayer.create(this, R.raw.refresh);
    }


    //if radio button was clicked
    public void onSongRadioButtonClicked(View view) {

        //if a song is playing
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_refresh://refresh
                Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show();
                mediaPlayer = MediaPlayer.create(this, R.raw.refresh);
                break;
            case R.id.radio_shamisen:
                Toast.makeText(this, "shamisen", Toast.LENGTH_SHORT).show();
                mediaPlayer = MediaPlayer.create(this, R.raw.shamisen);
                break;
        }
    }

    public void start(View view) {
        mediaPlayer.start();
    }

    public void pause(View view) {
        mediaPlayer.pause();
    }

    public void reset(View view) {
        mediaPlayer.stop();
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.seekTo(0);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Set Font Size");
        final Resources res = getResources();
        final String[] items = res.getStringArray(R.array.string_array_font_size);

        alertDialog.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    radio_refresh.setTextSize(10);
                    radio_shamisen.setTextSize(10);
                } else if (which == 1) {
                    radio_refresh.setTextSize(18);
                    radio_shamisen.setTextSize(18);
                }else {
                    radio_refresh.setTextSize(24);
                    radio_shamisen.setTextSize(24);
                }

            }
        });
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
        return true;
    }
}
