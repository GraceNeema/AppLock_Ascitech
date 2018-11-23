package com.example.lenovo.applock_ascitech;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 11/23/2018.
 */

public class LockScreen extends Activity {
    Button b1,b2;
    EditText Edtxt1, Edtxt2;
    PackageManager  manager;
    private List<AppList> apps;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        manager=getPackageManager();
        apps = new ArrayList<AppList>();

        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        Edtxt1=(EditText)findViewById(R.id.editText);
        Edtxt2=(EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Edtxt1.getText().toString().equals("admin") &&
                        Edtxt2.getText().toString().equals("123")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    LockScreen.this.onBackPressed();


                  //  LockScreen.this.onBackPressed();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    }

