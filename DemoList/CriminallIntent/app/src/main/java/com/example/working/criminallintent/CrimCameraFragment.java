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

    /*相机应该放在onResume和onPause回调中打开和释放相机资源
    * 使用open以及release来删除和添加camera,不过hardware类里面的不建议使用了，需要知道为什么
    *
    * 如何将Camera与SurfaceView相连接
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
    @SuppressWarnings("deprecation") //消除弃用的警告信息
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
            //获取设备支持的最佳尺寸
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
                //SurfaceView创建的时候调用
                try {
                    if (_camera != null) {
                        //连接Camera与Surface
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
                //Camera.Size s = null;// 这个为毛线是空啊？  这个表示预览大小为空
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
                    //定制在Surface绘制帧
                    _camera.stopPreview();
                }
            }
        });
        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

}
