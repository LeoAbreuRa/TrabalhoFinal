package com.senac.cadanimes.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.senac.cadanimes.R;
import com.senac.cadanimes.control.MainControl;

public class MainActivity extends AppCompatActivity {

    private MainControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = new MainControl(this);
    }

    public void entrar(View v){
        control.entrarAction();
    }

    public void sair(View v){
        control.sairAction();
    }
}
