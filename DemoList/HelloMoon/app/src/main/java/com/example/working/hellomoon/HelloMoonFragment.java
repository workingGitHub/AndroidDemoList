package com.example.working.hellomoon;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2015/10/25 0025.
 */
public class HelloMoonFragment extends Fragment {
    Button start;
    Button stop;
    Button pause;
    autoPlay mPlayer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.layout_fragment,null);
        start = (Button)v.findViewById(R.id.StartMusic);
        stop = (Button)v.findViewById(R.id.StopMusic);
        pause = (Button)v.findViewById(R.id.PauseMusic);
        mPlayer = new autoPlay();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.Play(getActivity());
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.Stop();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.pause();
            }
        });
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.Stop();
    }
}
