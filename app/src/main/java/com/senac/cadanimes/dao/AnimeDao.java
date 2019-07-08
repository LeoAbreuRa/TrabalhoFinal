package com.senac.cadanimes.dao;

import android.content.Context;

import com.senac.cadanimes.dao.helper.DaoHelper;
import com.senac.cadanimes.model.Anime;

public class AnimeDao extends DaoHelper<Anime> {


    public AnimeDao(Context c) {
        super(c, Anime.class);
    }
}
