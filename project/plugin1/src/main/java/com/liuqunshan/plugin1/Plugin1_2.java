package com.liuqunshan.plugin1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by liuqunshan on 2017/2/8.
 */

public class Plugin1_2 extends Activity {

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
                startActivity(new Intent(Plugin1_2.this, Plugin1_1.class));
            }
        });

        setContentView(t);
    }
}
