package com.liuqunshan.plugin2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.liuqunshan.base.ICallback;
import com.liuqunshan.base.IService;

/**
 * Created by liuqunshan on 2017/2/8.
 */

public class Plugin2_2 extends Activity {

    private static final String TAG = Plugin2_2.class.getSimpleName();

    private IService service;
    private ICallback.Stub callback = new ICallback.Stub() {
        @Override
        public void onValueReturn(boolean end, String val) throws RemoteException {
            Log.e(TAG, "on value async return:  " + end + "  " + val);
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder svc) {
            Log.e(TAG, "onServiceConnected: " + name + " " + svc);
            service = IService.Stub.asInterface(svc);
            test();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: " + name);
            service = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView t = new TextView(this);
        t.setTextSize(40);
        t.setTextColor(Color.RED);
        t.setText(getClass().getCanonicalName());
        t.setClickable(true);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Plugin2_2.this, Plugin2_1.class));
            }
        });

        setContentView(t);

//        final Intent intent = new Intent("test_plugin_PluginService");
        final Intent intent = new Intent();
        intent.setClassName("com.liuqunshan.plugin1", "com.liuqunshan.plugin1.PluginService");
        Log.e(TAG, "start service:   " + startService(intent));
        Log.e(TAG, "bind service:   " + bindService(intent, connection, BIND_AUTO_CREATE));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private void test() {
        try {
            Log.e(TAG, "get value sync:  " + service.getValueSync());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            service.getValueAsync(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
