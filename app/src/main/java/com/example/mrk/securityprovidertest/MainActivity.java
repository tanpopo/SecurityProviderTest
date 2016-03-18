package com.example.mrk.securityprovidertest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.security.Provider;
import java.security.Security;

/*
ref:
https://docs.oracle.com/javase/7/docs/api/javax/crypto/spec/GCMParameterSpec.html
http://www.techscore.com/tech/Java/JavaSE/JCE/9/#jce9-3
GCM用AlgorithmParametersの使い方
https://github.com/wsargent/jce-aes-cipher-poc/blob/master/src/main/java/com/tersesystems/cipher/aes/AESCipherService.java
AlgorithmParameters.init(byte[])は、おそらくAlgParamsGCM.engineInit(byte[] params)につながる
http://tools.oesf.biz/android-6.0.0_r1.0/xref/external/bouncycastle/bcprov/src/main/java/org/bouncycastle/jcajce/provider/symmetric/AES.java#397
 */
public class MainActivity extends AppCompatActivity {
    private static String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       Button updateProviderListButton = (Button) findViewById(R.id.getProviderListButton);
        updateProviderListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProviderList();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void updateProviderList() {
        Provider providers[] = Security.getProviders();
        String outText = "";
        for (Provider provider : providers) {
            outText += provider.getName() + "\n";
        }
        TextView outputView = (TextView) findViewById(R.id.outputView);
        outputView.setText(outText);

        Log.d(tag, "hogehoge");
    }
}
