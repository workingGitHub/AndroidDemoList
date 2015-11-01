package com.example.working.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/10/25 0025.
 */
public class autoPlay {
    private MediaPlayer mPlayer;

    public void Stop()
    {
        if(mPlayer != null) {
            Log.v("Test1",String.format("Stop [%s]",mPlayer.toString()));
            mPlayer.release();
            mPlayer = null;
        }
    }
    public void pause()
    {
        if(mPlayer != null) {
            Log.v("Test1", String.format("pause [%s]", mPlayer.toString()));
            mPlayer.pause();

        }
    }
    public void Play(Context c)
    {
        if(mPlayer == null) {
            Log.v("Test1", String.format("Creat begin "));
            mPlayer = MediaPlayer.create(c, R.raw.one_small_step);
            Log.v("Test1", String.format("Creat [%s]", mPlayer.toString()));
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Stop();
                }
            });
            Log.v("Test1", String.format("setOnCompletionListener [%s]", mPlayer.toString()));
        }
        mPlayer.start();
    }
}
