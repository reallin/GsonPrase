package com.example.linxj.gson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import Model.Easy;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.easybtn)
    Button easybtn;
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.arraybtn)
    Button arraybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivity);
        ButterKnife.bind(this);

        btn = (Button) super.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, BookDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        easybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Easy easy = new Gson().fromJson(new MyJsonParse().easyParse(), Easy.class);

                Toast.makeText(MainActivity.this, easy.getName() + " " + easy.getAge(), Toast.LENGTH_SHORT).show();
            }
        });
        arraybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type type = new TypeToken<ArrayList<Easy>>(){}.getType();
                ArrayList<Easy> list = new Gson().fromJson(new MyJsonParse().arrayParse(), type);
                Iterator i = list.iterator();
                while(i.hasNext()) {
                    Easy e = (Easy)i.next();
                    Toast.makeText(MainActivity.this, e.getAge() + " " + e.getName(), Toast.LENGTH_SHORT).show();
                }
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
}
