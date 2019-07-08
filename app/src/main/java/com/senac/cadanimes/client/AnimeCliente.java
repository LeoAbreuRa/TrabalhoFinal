package com.senac.cadanimes.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.senac.cadanimes.model.Anime;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AnimeCliente {

    private static final String BASE_URL =
            "http://192.168.0.16:8080/CatalogoAnime-API/api";
    private static final String URL = "/anime";
    private AsyncHttpClient client;
    private Activity activity;
    private AlertDialog dlgCarregando;

    public AnimeCliente(Activity activity) {
        this.activity = activity;
        dlgCarregando = (new AlertDialog.Builder(activity)).create();
        dlgCarregando.setTitle("Aguarde");
        dlgCarregando.setMessage("Requisitando banco de dados...");
        dlgCarregando.setCanceledOnTouchOutside(false);
    }

    public void getAnimesListView(final ArrayAdapter<Anime> adapterAnime){
        client = new AsyncHttpClient();
        client.get(BASE_URL + URL, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                dlgCarregando.show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String resJSON = new String(bytes);

                Gson gson = new Gson();
                Type usuariosListType = new TypeToken<ArrayList<Anime>>(){}.getType();
                List<Anime> listAnimes = gson.fromJson(resJSON, usuariosListType);
                adapterAnime.addAll(listAnimes);
                dlgCarregando.dismiss();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(activity, "Falhou a requisição Cod.: "+i, Toast.LENGTH_SHORT).show();
                dlgCarregando.dismiss();
            }
            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
                Toast.makeText(activity, "Tentativa número "+retryNo, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ler(Integer id, TextView tv){
        client = new AsyncHttpClient();
        client.get(BASE_URL + URL + "/" + id, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public void removerAnime(
            final ArrayAdapter<Anime> adapterAnime,
            Integer id){
        client = new AsyncHttpClient();
        client.delete("api/anime/"+id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dlgCarregando.show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String res = new String(bytes);
                Gson g = new Gson();
                Anime c = g.fromJson(res, Anime.class);
                adapterAnime.remove(c);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                dlgCarregando.dismiss();
            }
        });

    }

    public void alterarAnime(ArrayAdapter<Anime> adapterAnime,
                                 Anime anime){

    }

    public void cadastrar(final ArrayAdapter<Anime> adapterAnime,
                          final Anime a){
        client = new AsyncHttpClient();
        client.post("api/anime/", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dlgCarregando.show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String res = new String(bytes);
                int newId = Integer.parseInt(res);
                a.setId(newId);
                adapterAnime.add(a);
                dlgCarregando.dismiss();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                dlgCarregando.dismiss();
            }
        });
    }

}
