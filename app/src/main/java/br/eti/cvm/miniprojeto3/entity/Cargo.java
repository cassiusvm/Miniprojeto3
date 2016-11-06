package br.eti.cvm.miniprojeto3.entity;

import android.content.ContentValues;

public class Cargo  {
    private Long id;
    private String nome;
    private ContentValues contentValues = new ContentValues();
    public static final String TABLE = "Cargo";
    public static final String _ID = "id";
    public static final String COLUMN_NOME = "nome";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ContentValues getContentValues() {
        this.contentValues.put(COLUMN_NOME,this.nome);
        return contentValues;
    }
}
