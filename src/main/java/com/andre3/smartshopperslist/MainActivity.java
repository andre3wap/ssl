package com.andre3.smartshopperslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andre3.smartshopperslist.fragments.MainMenu;
import com.andre3.smartshopperslist.fragments.ScreenOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScreenOptions mainmenufrag = new ScreenOptions();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainmenufrag).commit();

    }
}
