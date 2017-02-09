package com.liuqunshan.plugin1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.liuqunshan.base.ICallback;
import com.liuqunshan.base.IService;

/**
 * Created by liuqunshan on 2017/2/9.
 */

public class PluginService extends Service {

    private static final String TAG = PluginService.class.getSimpleName();

    private int value = 1000;

    private IService.Stub stub = new IService.Stub() {
        @Override
        public void getValueAsync(ICallback cbk) throws RemoteException {
            Log.e(TAG, "@@@@@@@@@@@@ getValueAsync:  " + cbk);
            if (cbk != null) {
                final int end = value + 10;
                for (int i = value; i < end; i++) {
                    cbk.onValueReturn(i == end - 1, "test string " + i);
                }
            }
        }

        @Override
        public int getValueSync() throws RemoteException {
            Log.e(TAG, "@@@@@@@@@@@@ getValueSync:  " + value);
            return value += 100;
        }
    };

    @Override
    public void onCreate() {
        Log.e(TAG, "@@@@@@@@@@@@ onCreate:  " + value);
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "@@@@@@@@@@@@ onBind:  " + intent);
        return stub;
//        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "@@@@@@@@@@@@ onUnbind:  " + intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "@@@@@@@@@@@@ onDestroy:  " + value);
        super.onDestroy();
    }
}
