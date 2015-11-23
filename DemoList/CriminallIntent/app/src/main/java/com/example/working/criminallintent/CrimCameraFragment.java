package com.example.working.criminallintent;

import android.app.Fragment;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/4 0004.
 */
public class CrimCameraFragment extends Fragment {
    private static final String TAG = "CrimCameraFragment";
    SurfaceView _sufaceview;
    Camera _camera;

    /*���Ӧ�÷���onResume��onPause�ص��д򿪺��ͷ������Դ
    * ʹ��open�Լ�release��ɾ�������camera,����hardware������Ĳ�����ʹ���ˣ���Ҫ֪��Ϊʲô
    *
    * ��ν�Camera��SurfaceView������
    * */

    @Override
    public void onResume() {
        super.onResume();
        _camera = Camera.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        if( _camera != null )
        {
            _camera.release();
            _camera = null;
        }
    }

    @Nullable
    @SuppressWarnings("deprecation") //�������õľ�����Ϣ
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frament_crime_camera, null);

        Button b = (Button)v.findViewById(R.id.btn_getpic);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        _sufaceview = (SurfaceView)v.findViewById(R.id.surface_camera);
        SurfaceHolder holder = _sufaceview.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        holder.addCallback(new SurfaceHolder.Callback() {
            //��ȡ�豸֧�ֵ���ѳߴ�
            private Camera.Size getbestSupportedSize(List<Camera.Size> sizes,int w,int h){
                Camera.Size bestSize = sizes.get(0);
                int largestArea = bestSize.width * bestSize.height;
                for(Camera.Size s:sizes)
                {
                    int area = s.width * s.height;
                    if(area > largestArea)
                    {
                        bestSize = s;
                        largestArea = area;
                    }
                }
                return bestSize;
            }
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //SurfaceView������ʱ�����
                try {
                    if (_camera != null) {
                        //����Camera��Surface
                        _camera.setPreviewDisplay(holder);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error surfaceCreated", e);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if (_camera == null) return;
                Camera.Parameters parameters = _camera.getParameters();
                //Camera.Size s = null;// ���Ϊë���ǿհ���  �����ʾԤ����СΪ��
                Camera.Size s = getbestSupportedSize(parameters.getSupportedPreviewSizes(),width,height);
                parameters.setPreviewSize(s.width, s.height);
                _camera.setParameters(parameters);
                try {
                    _camera.startPreview();
                } catch (Exception e) {
                    _camera.release();
                    _camera = null;
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (_camera != null) {
                    //������Surface����֡
                    _camera.stopPreview();
                }
            }
        });
        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

}
