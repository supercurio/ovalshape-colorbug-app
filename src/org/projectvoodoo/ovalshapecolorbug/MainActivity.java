
package org.projectvoodoo.ovalshapecolorbug;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private static boolean sFilterEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set Activity Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);
        findViewById(R.id.layoutMain).setOnClickListener(this);

        setMaxBrightness();
        setMetas();
        setColorFilter();
    }

    private void setMaxBrightness() {

        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1;
        getWindow().setAttributes(layout);
    }

    private void setColorFilter() {

        PorterDuffColorFilter filter = sFilterEnabled ?
                new PorterDuffColorFilter(
                        0x7FFFFFFF,
                        PorterDuff.Mode.SCREEN) : null;

        applyColorFilter(filter, R.id.ImageRect);
        applyColorFilter(filter, R.id.imageOval);

        ((ImageView) findViewById(R.id.refImage))
                .setImageResource(sFilterEnabled ? R.drawable.reference_filter
                        : R.drawable.reference_normal);
    }

    private void applyColorFilter(PorterDuffColorFilter filter, int id) {
        ((ImageView) findViewById(id)).setColorFilter(filter);
    }

    private void switchFilterEnabled() {
        sFilterEnabled = !sFilterEnabled;

        ((TextView) findViewById(R.id.filterIndicator))
                .setText(sFilterEnabled ? R.string.with_filter : R.string.without_filter);

        setColorFilter();
    }

    private void setMetas() {
        TextView versionCredits = (TextView) findViewById(R.id.versionCredits);
        versionCredits.setText(getString(R.string.app_name) + " "
                + Utils.getVersionMetas(this) + " by supercurio"
                + "\nDevice: " + Build.MODEL + ", " + Build.FINGERPRINT);
    }

    @Override
    public void onClick(View v) {
        switchFilterEnabled();
    }
}
