package com.senac.cadanimes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.senac.cadanimes.R;
import com.senac.cadanimes.control.PesqControl;

public class PesqAnimeActivity extends AppCompatActivity {

    private PesqControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesq_anime);

        control = new PesqControl(this);
    }

    public void cadastrar(View v){
        control.cadastrarAction();
    }

    public void voltar(View v){
        control.voltarAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        control.configurarListView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        control.onActivityResult(requestCode, resultCode, data);
    }

}
