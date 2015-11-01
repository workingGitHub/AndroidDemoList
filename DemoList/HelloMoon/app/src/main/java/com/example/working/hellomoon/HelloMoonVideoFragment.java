package com.example.working.hellomoon;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

/**
 * Created by Administrator on 2015/10/25 0025.
 */
public class HelloMoonVideoFragment extends Fragment {
    VideoView _videoview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.layout_video,null);
        _videoview = (VideoView)v.findViewById(R.id.Video);
        return v;
    }
}
