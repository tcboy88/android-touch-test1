package net.hanjava.test;

import android.app.Activity;
import android.os.Bundle;

public class TouchActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TouchView v = new TouchView(this);
        setContentView(v);
    }
}