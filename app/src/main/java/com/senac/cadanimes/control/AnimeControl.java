package com.senac.cadanimes.control;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.senac.cadanimes.R;
import com.senac.cadanimes.dao.AnimeDao;
import com.senac.cadanimes.model.Anime;
import com.senac.cadanimes.view.PesqAnimeActivity;

import java.sql.SQLException;

public class AnimeControl {

    private Activity activity;
    private EditText editNome;
    private EditText editEpi;
    private EditText editGenero;

    private Anime anime = null;
    private Anime anime2;
    private AnimeDao animeDao;

    public AnimeControl(Activity activity) {
        this.activity = activity;
        anime2 = (Anime) activity.getIntent().getSerializableExtra("anime");

        animeDao = new AnimeDao(this.activity);
        initComponents();

        if (anime2 !=null){
            editNome.setText(anime2.getNome());
            editEpi.setText(anime2.getEpisodio());
            editGenero.setText(anime2.getGenero());
        }
    }

    private void initComponents(){
        editNome = activity.findViewById(R.id.editNome);
        editEpi = activity.findViewById(R.id.editEpi);
        editGenero = activity.findViewById(R.id.editGenero);
    }

    public void concluirAction() {
        if (validarAnime()) {
            if (anime == null) {
                anime = new Anime();
            }
            anime.setNome(editNome.getText().toString());
            anime.setEpisodio(editEpi.getText().toString());
            anime.setGenero(editGenero.getText().toString());

            Intent it = new Intent();
            it.putExtra("nome", anime);
            activity.setResult(activity.RESULT_OK, it);
            activity.finish();
        }
    }

    public void voltarAction() {
        activity.finish();
    }

    private boolean validarAnime() {
        if (editNome.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, R.string.erro_nome, Toast.LENGTH_SHORT).show();
            return false;
        } else if (editEpi.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, R.string.erro_epi, Toast.LENGTH_SHORT).show();
            return false;
        } else if (editGenero.getText().toString().trim().isEmpty()){
            Toast.makeText(activity, R.string.erro_genero, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
}
}
