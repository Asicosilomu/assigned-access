package com.asicosilomu.assigned;

import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AssignedActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // do literally fucking nothing
    }

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
        setContentView(R.layout.activity_assigned);
        Intent launch = getIntent();
        if(!Objects.equals(launch.getStringExtra("launchedFromMain"), "yes"))
        {
            System.exit(0);
        }
        Button txtedtb = findViewById(R.id.txtedtb);
        if(!Objects.equals(launch.getStringExtra("textEditorAllowed"), "yes"))
        {
            txtedtb.setEnabled(false);
            txtedtb.setClickable(false);
        }
        txtedtb.setOnClickListener(view -> {
            if(!Objects.equals(launch.getStringExtra("textEditorAllowed"), "yes"))
            {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        AssignedActivity.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("Error");

                // Setting Dialog Message
                alertDialog.setMessage("This feature is blocked.");

                // Showing Alert Message
                alertDialog.show();
            } else {
                Intent i = new Intent(this, AssignedTextEditor.class);
                startActivity(i);
            }
        });
        Button webbrowserb = findViewById(R.id.webbrowserb);
        if(!Objects.equals(launch.getStringExtra("webBrowserAllowed"), "yes"))
        {
            webbrowserb.setEnabled(false);
            webbrowserb.setClickable(false);
        }
        webbrowserb.setOnClickListener(view -> {
            if(!Objects.equals(launch.getStringExtra("webBrowserAllowed"), "yes"))
            {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        AssignedActivity.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("Error");

                // Setting Dialog Message
                alertDialog.setMessage("This feature is blocked.");

                // Showing Alert Message
                alertDialog.show();
            } else {
                Intent i = new Intent(this, AssignedWebBrowser.class);
                i.putExtra("webOtherPagesAllowed", launch.getStringExtra("webOtherPagesAllowed"));
                i.putExtra("webDefaultPage", launch.getStringExtra("webDefaultPage"));
                startActivity(i);
            }
        });

}}