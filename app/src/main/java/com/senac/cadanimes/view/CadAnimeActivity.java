package com.senac.cadanimes.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.senac.cadanimes.R;
import com.senac.cadanimes.control.AnimeControl;

public class CadAnimeActivity extends AppCompatActivity {

    private AnimeControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_anime);

        control = new AnimeControl(this);
    }

    public void concluir(View v){
        control.concluirAction();
    }

    public void voltar(View v){
        control.voltarAction();
    }

}
