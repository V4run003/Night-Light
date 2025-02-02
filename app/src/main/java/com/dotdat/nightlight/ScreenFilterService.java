package com.dotdat.nightlight;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class ScreenFilterService extends Service {
    public static int STATE_ACTIVE = 0;
    public static int STATE_INACTIVE = 1;

    public static int STATE;
    static {
        STATE = STATE_INACTIVE;
    }

    private SharedMemory mSharedMemory;
    private View mView;

    public ScreenFilterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mSharedMemory = new SharedMemory(this);
        mView = new LinearLayout(this);
        mView.setBackgroundColor(mSharedMemory.getColor());

        WindowManager.LayoutParams layoutParams;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
           layoutParams= new WindowManager.LayoutParams(
                   WindowManager.LayoutParams.FILL_PARENT,
                   WindowManager.LayoutParams.FILL_PARENT,
                   WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                   280,
                   PixelFormat.TRANSLUCENT
           );

        }
        else{
            layoutParams = new WindowManager.LayoutParams(
                   WindowManager.LayoutParams.FILL_PARENT,
                   WindowManager.LayoutParams.FILL_PARENT,
                   WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                   280,
                   PixelFormat.TRANSLUCENT
            );

        }
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        windowManager.addView(mView, layoutParams);
        STATE = STATE_ACTIVE;
        Log.d("TAGGGGG","A");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        windowManager.removeView(mView);
        STATE = STATE_INACTIVE;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mView.setBackgroundColor(mSharedMemory.getColor());
        return super.onStartCommand(intent, flags, startId);

    }
}
