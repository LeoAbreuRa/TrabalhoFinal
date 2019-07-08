package com.senac.cadanimes.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "anime")
public class Anime implements Serializable {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "nome", canBeNull = false, width = 255)
    private String nome;
    @DatabaseField(columnName = "episodio",canBeNull = false, width = 50)
    private String episodio;
    @DatabaseField(columnName = "genero",canBeNull = false, width = 255)
    private String genero;

    public Anime() {
    }

    public Anime(Integer id, String nome, String episodio, String genero) {
        this.id = id;
        this.nome = nome;
        this.episodio = episodio;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEpisodio() {
        return episodio;
    }

    public void setEpisodio(String episodio) {
        this.episodio = episodio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + episodio + " - " + genero;
    }

}
