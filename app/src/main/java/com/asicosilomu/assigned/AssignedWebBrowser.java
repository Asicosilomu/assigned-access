package com.asicosilomu.assigned;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

import java.util.Objects;

public class AssignedWebBrowser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1)
        {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
            // KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            // if(keyguardManager!=null)
            // keyguardManager.requestDismissKeyguard(this, null);
        }
        else
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
        setContentView(R.layout.activity_assigned_web_browser);
        Intent launch = getIntent();
        WebView web = findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(launch.getStringExtra("webDefaultPage"));
        if (Objects.equals(launch.getStringExtra("webOtherPagesAllowed"), "yes")) {
            BrowserController controller = new BrowserController();
            controller.setWebviewNavigation(true);
            web.setWebViewClient(controller);
        } else {
            BrowserController controller = new BrowserController();
            controller.setWebviewNavigation(false);
            web.setWebViewClient(controller);
        }
    }
}