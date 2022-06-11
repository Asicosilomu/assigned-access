package com.asicosilomu.assigned;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox txtEdtChk = (CheckBox) findViewById(R.id.textEditorChk);
        CheckBox webBrowserChk = (CheckBox) findViewById(R.id.webBrowserChk);
        CheckBox webOtherPagesAllowedChk = (CheckBox) findViewById(R.id.webOtherPagesAllowedChk);
        EditText webDefaultPage = (EditText) findViewById(R.id.webDefaultPageEdt);
        Button ass = findViewById(R.id.assign);
        ass.setOnClickListener(view -> {
            Intent asgi = new Intent(this, AssignedActivity.class);
            asgi.putExtra("launchedFromMain","yes");
            if(txtEdtChk.isChecked()) {
                asgi.putExtra("textEditorAllowed","yes");
            } else {
                asgi.putExtra("textEditorAllowed","no");
            }
            if(webBrowserChk.isChecked()) {
                asgi.putExtra("webBrowserAllowed","yes");
            } else {
                asgi.putExtra("webBrowserAllowed","no");
            }
            if(webOtherPagesAllowedChk.isChecked()) {
                asgi.putExtra("webOtherPagesAllowed","yes");
            } else {
                asgi.putExtra("webOtherPagesAllowed","no");
            }
            asgi.putExtra("webDefaultPage", webDefaultPage.getText().toString());
            startActivity(asgi);
        });
    }
}