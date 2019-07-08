package com.senac.cadanimes.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import com.senac.cadanimes.view.PesqAnimeActivity;

public class MainControl {

    private Activity activity;

    public MainControl(Activity activity) {
        this.activity = activity;
    }

    public void entrarAction(){
        Intent it = new Intent(activity, PesqAnimeActivity.class);
        activity.startActivity(it);
    }

    public void sairAction(){
        AlertDialog.Builder alerta;
        alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Fechando App");
        alerta.setMessage("Deseja fechar  o app?");
        alerta.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        alerta.setNegativeButton("Nâo", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
            }
        });
        alerta.show();
    }

}
