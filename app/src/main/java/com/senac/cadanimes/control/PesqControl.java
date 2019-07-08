package com.senac.cadanimes.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.senac.cadanimes.R;
import com.senac.cadanimes.client.AnimeCliente;
import com.senac.cadanimes.dao.AnimeDao;
import com.senac.cadanimes.model.Anime;
import com.senac.cadanimes.view.CadAnimeActivity;

import java.sql.SQLException;

public class PesqControl {

    private Activity activity;
    private ListView lvAnime;
    private ArrayAdapter<Anime> adapterAnimes;
    private AnimeDao animeDao;
    private Anime anime;
    private AnimeCliente animeCliente;

    public PesqControl(Activity activity) {
        this.activity = activity;
        animeDao = new AnimeDao(activity);
        initcomponets();
    }

    public void initcomponets() {
        lvAnime = activity.findViewById(R.id.lvAnimes);
        configurarListView();
        cliqueCurto();
        cliqueLongo();
    }

    public void configurarListView() {
        try {
            adapterAnimes = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    animeDao.getDao().queryForAll()
            );
            lvAnime.setAdapter(adapterAnimes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarAction() {
        Intent it = new Intent(activity, CadAnimeActivity.class);
        activity.startActivityForResult(it, 666);
    }

    private void cliqueCurto() {
        lvAnime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                anime = adapterAnimes.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Visualizando Anime");
                alerta.setMessage(anime.toString());
                alerta.setNeutralButton("Fechar", null);
                alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        anime = (Anime) lvAnime.getItemAtPosition(position);
                        Intent it = new Intent(activity, CadAnimeActivity.class);
                        it.putExtra("anime", anime);
                        activity.startActivityForResult(it, 999);
                    }
                });
                alerta.show();
            }
        });
    }

    private void cliqueLongo() {
        lvAnime.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                anime = adapterAnimes.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Excluindo Anime");
                alerta.setMessage("Deseja realmente excluir o Anime " + anime.getNome() + "?");
                alerta.setIcon(android.R.drawable.ic_menu_delete);
                alerta.setNeutralButton("Não", null);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            animeDao.getDao().delete(anime);
                            adapterAnimes.remove(anime);
                            anime = null;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alerta.show();
                return true;
            }
        });
    }

    public void voltarAction() {
        activity.finish();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 666 && resultCode == activity.RESULT_OK) {
            anime = (Anime) data.getSerializableExtra("nome");
            anime.setNome(anime.getNome());
            try {
                animeDao.getDao().create(anime);
                Toast.makeText(activity, R.string.cad_sucesso, Toast.LENGTH_LONG).show();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            anime = null;
        } else if (requestCode == 999 && resultCode == activity.RESULT_OK) {
            Anime a = (Anime) data.getSerializableExtra("nome");
            anime.setNome(a.getNome());
            anime.setEpisodio(a.getEpisodio());
            anime.setGenero(a.getGenero());
            adapterAnimes.notifyDataSetChanged();
            try {
                animeDao.getDao().update(anime);
                Toast.makeText(activity, R.string.edit_sucesso, Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            anime = null;
        } else if (resultCode == activity.RESULT_CANCELED) {
            Toast.makeText(activity, "Cancelou", Toast.LENGTH_SHORT).show();
        }

    }
}
